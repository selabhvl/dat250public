
<script>
  let counter = 0;

  function increment() {
    counter = counter + 1;
  }

  const result =  fetch("/locations").then((response) => response.json())

</script>

<h1>Hello World</h1>
<p>Counter is {counter}</p>
<button on:click={increment}>Click me</button>

<div>
{#await result}
  its is loading...
{:then ready}
  {#each ready as location}
    <p>{location.name} {location.latitude},{location.longitude}</p>
    {/each}
{:catch error}
  {error}
  {/await}
</div>



<style>
  * {
    margin: 0;
    font-family: "Fira Sans", sans-serif;
  }
</style>