package no.hvl.dat250.l04.examples.domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.ArrayList;
import java.util.List;

public class Filesystem {

    public static abstract class Node {
        private String name;

        @JsonBackReference
        private Node parent;

        public Node() {
        }

        public Node(String name, Node parent) {
            this.name = name;
            this.parent = parent;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }
    }

    public static class File extends Node {

        private long sizeInBytes;

        public File(String name, Node parent, long sizeInBytes) {
            super(name, parent);
            this.sizeInBytes = sizeInBytes;
        }

        public File() {
        }

        public long getSizeInBytes() {
            return sizeInBytes;
        }

        public void setSizeInBytes(long sizeInBytes) {
            this.sizeInBytes = sizeInBytes;
        }
    }

    public static class Folder extends Node {

        @JsonManagedReference
        private List<Node> contents = new ArrayList<>();

        public Folder() {
            super();
        }

        public List<Node> getContents() {
            return contents;
        }

        public void setContents(List<Node> contents) {
            this.contents = contents;
        }
    }

    public static class Symlink extends Node {
        private Node linksTo;

        public Symlink() {
        }

        public Symlink(String name, Node parent, Node linksTo) {
            super(name, parent);
            this.linksTo = linksTo;
        }

        public Node getLinksTo() {
            return linksTo;
        }

        public void setLinksTo(Node linksTo) {
            this.linksTo = linksTo;
        }
    }

}
