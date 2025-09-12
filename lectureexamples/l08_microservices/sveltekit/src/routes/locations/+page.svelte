<script>
    let { data } = $props()
    let result = $state(data.locations);

    console.info("I am running on the client")
</script>

<div class="content">
    <h1>Weather Observations</h1>
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
            {#each ready as location}
                <tr>
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
    <form method="POST">
    <div class="create-location">
        <h2>Create new location</h2>
        <label for="create-name">Name:</label>
        <!-- The bind: syntax is used to establish two-way databinding with a variable -->
        <input  id="create-name" type="text" name="location">
        <label for="create-lat">Latitude:</label>
        <input  id="create-lat" type="text" name="latitude">
        <label for="create-lon">Longitude:</label>
        <input  id="create-lon" type="text" name="longitude">
        <button>Create</button>
    </div>
    </form>
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
