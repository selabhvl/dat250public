<script>
    let messages = [];
    let currentMsg = "";
    let ws;
    let status = "disconnected";

    function connect() {
        ws = new WebSocket("http://localhost:8081/ws/chat");
        messages = [];

        ws.addEventListener("message", (event) => {
            messages.push(event.data);
            messages = messages;
        });

        status = "connected";

    }
    function send() {
        if (status === "connected") {
            ws.send(currentMsg);
            currentMsg = "";
        }
    }

    function close() {
        ws.close();
        ws = null;
        status = "disconnected"
    }
</script>

<div class="state">{status}</div>

<div class="chat-messages">
    {#each messages as msg}
    <p>{msg}</p>
    {/each}
</div>

<div class="write-message">
    <div>
    <label for="msg">Message:</label>
    <input type="text" bind:value={currentMsg}>
    </div>
    <button on:click={send}>Send</button>
    <button on:click={connect}>Connect</button>
    <button on:click={close}>Disconnect</button>
</div>

