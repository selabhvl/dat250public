# Docker / Container Technology

## Technical Background

A container is basically a combination of three concepts: 
- `chroot`
- `namepsaces`
- `cgroups`

You can try these (or at least the first two) out yourself.
For this you either need to be on a Linux system, or you can run a Ubuntu image via Docker.

```shell
docker run --rm -it --privileged ubuntu:latest
```

### chroot

First, you can create your own "_mini file tree_". 

```shell
mkdir -p /home/isolated
cd /home/isolated
touch empty.file
echo "hello" > greeting.txt
mkdir bin 
mkdir lib
```

Before, you can switch into that file system, you need to copy some essential binaries (including their library dependencies)
into the new root.

```shell
# copying bash, kill and ls
cp /usr/bin/bash /usr/bin/ls /usr/bin/kill /home/isolated/bin
# copying the shared object libraries
cp /lib/aarch64-linux-gnu/libselinux.so.1 \
 /lib/aarch64-linux-gnu/libc.so.6 \
 /lib/ld-linux-aarch64.so.1 \
 /lib/aarch64-linux-gnu/libpcre2-8.so.0 \
 /lib/aarch64-linux-gnu/libtinfo.so.6 \
 /home/isolated/lib/
```

> **Side Remarks**
> 
> Wondering where how do you find out what libraries you need to copy? You can use `ldd <binary>`, which will print the 
> required libraries and their location

Now, you are able to change into the new environment:
```shell
chroot /home/isolated /bin/bash
```

### namespaces

`chroot` allows to isolate a process in a demarcated part of the filesystem. 
However, inside a chroot one can still access the process of the "host" system.

For instance, you can try to start a background process
```shell
yes "This is fine" > /dev/null 2> /dev/null &
```

check that the process is running with:
```shell
ps aux
```

and remember the process id of `yes` background task.

Now, go into the `chroot` again and try to `kill` that process

```bash
chroot /home/isolated /bin/bash 
kill <PID>
exit
```

You will see that the background process has been terminated. In a properly isolated environment, this should not have happened.
Namespaces are a way to facilitate just this via the `unshare` command:

```shell
unshare --mount --uts --ipc --net --pid --fork --user --map-root-user chroot /home/isolated /bin/bash
```

### cgroups


Until here, we hav seen how we can isolate the filesystem with the help of `chroot` and the operating system processes with the help
of namespaces (`unshare`). 
Still all processes share the same operating system resources. 
cgroups (control groups) are a way to limit the share of available computing resources to individual processes.
Control groups do not expose an explicit API in the form of binaries, instead one interacts with control groups by modifying
files and folders at a specific location:

This location is `/sys/fs/cgroup`.
Creating a subdirectory in this folder means creating a new `cgroup`, e.g. `mkdir sandbox` (requires to run the container with `--privileged`).
A newly created control group does not have any _controllers_ enabled (tho enable them you first, need to move all
current process id's into a temporary control group, then enable subgroup controllers with `echo "+cpuset +cpu +io +memory +hugetlb +pids +rdma" > /sys/fs/cgroup/cgroup.subtree_control`).
The file `/sys/fs/cgroup/cgroup.procs` contains the process id's of all the processes that belong to this control group.
Files such as `cpu.max`, `cpu.threads`, `memory.max` can be used to defined limits on the available resources.


## Docker




