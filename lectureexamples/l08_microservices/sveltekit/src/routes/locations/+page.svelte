<script>
    // Svelte component comprise behaviour written in a <script> tag (i.e. this one),
    // structure given by arbitrary HTML or nested Svelte components and presentation
    // specified in a <style> tag.

    // other components or Svelte-internal functions are imported using the JS module import syntax.
    import {onMount} from "svelte";

    // component variables (internal state) used for value binding in the form below
    let newLocationName = $state();
    let newLocationLat = $state();
    let newLocationLon = $state();

    let result = $state();

    // initially lead the existing weather locations via GET request.
    result = fetch("http://localhost:8080/locations").then((response) => {
            return response.json();
        }
    )


    // This function gets executed when `create` button is clicked.
    // It performs a POST request to the API to create a new location.
    function createNewLocation() {
        fetch("http://localhost:8080/locations", {
            method: "POST",
            body: JSON.stringify({
                name: newLocationName,
                latitude: Number.parseFloat(newLocationLat),
                longitude: Number.parseFloat(newLocationLon)
            }),
            headers: {
                "Content-Type": "application/json"
            }
        }).then((response) => {
            if (response.status === 201) {
                alert(`Location ${newLocationName} successfully created!`);
                newLocationName = "";
                newLocationLat = "";
                newLocationLon = "";
            }
        }).catch((error) => {
            alert(error.message);
        })
    }

    // The Svelte framework methods onMount can be used to execute code after the component has been rendered.
    onMount(() => {
        // here we use it register a background task (using JS setInterval() function) that every 5 sec (5000 ms)
        // refreshes the weather location data by calling the GET request again
        const intervalId = setInterval(() => {
            result = fetch("http://localhost:8080/locations").then((response) => response.json());
        }, 5000);

        // the onMount function can return a callback function that "clears up" after a component has been removed.
        // in this case, the background task should be removed by calling clearInterval()
        return () => clearInterval(intervalId);
    })
</script>

<!-- Basically normal HMTL -->
<div class="content">
    <h1>Weather Observations</h1>
    <!-- Svelte offers special templating synatx using curly braces, here for awaiting a Promise/Future -->
    {#await result}
        its is loading...
    {:then ready}
        <table>
            <thead>
            <tr>
                <th>Location</th>
                <th>Latitude</th>
                <th>Longitude</th>
                <th>Precipitation</th>
                <th>Temperature</th>
            </tr>
            </thead>
            <tbody>
            <!-- and here for repeating elements based on the contents of a JS array -->
            {#each ready as location}
                <tr>
                    <!-- the curly brace evauluate a JS expression -->
                    <td>{location.name}</td>
                    <td>{location.latitude}</td>
                    <td>{location.longitude}</td>
                    <td>{location.precipitation}</td>
                    <td>{location.temperature}</td>
                </tr>
            {/each}
            </tbody>
        </table>
    {:catch error}
        {error}
    {/await}
    <div class="create-location">
        <h2>Create new location</h2>
        <label for="create-name">Name:</label>
        <!-- The bind: syntax is used to establish two-way databinding with a variable -->
        <input  id="create-name" type="text" bind:value={newLocationName}>
        <label for="create-lat">Latitude:</label>
        <input  id="create-lat" type="text" bind:value={newLocationLat}>
        <label for="create-lon">Longitude:</label>
        <input  id="create-lon" type="text" bind:value={newLocationLon}>
        <!-- The on:click registers an event handler, i.e. a function to be called (Command pattern) -->
        <button onclick={createNewLocation} >Create</button>
    </div>
</div>


<style>

    .content {
        width: 960px;
        margin: 0 auto;
        padding-top: 1rem;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: space-evenly;
        gap: 1.5rem;
    }
    .content table tr td {
        text-align: left;
        border: 1px solid #ccc;
    }
    .content table tr th {
        border: 1px solid #ccc;
        text-align: left;

    }
    .create-location {
        width: 50%;
        padding: 1rem;
        gap: 0.5rem;
        display: grid;
        grid-template-columns: 1fr 1fr;
        border: 1px solid #eee;
        box-shadow: 2px 2px 4px -3px #eee;
    }
    .create-location h2 {
        grid-column: span 2;
    }
    .create-location button {
        grid-column: span 2;
    }

</style>
