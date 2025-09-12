/* This file is normal JavaScript that is run by the node.js server, no Svelte magic here */

/**
 * The Load function reacts on GET requests, i.e. when the Svelte page is loaded initially.
 */
export const load = async function (params) {
    console.info("I am running on the server :)")
    let locationsResponse = await fetch("http://localhost:8080/locations");
    let locationsJson = await locationsResponse.json()
    return {
        locations: locationsJson
    }
}

/**
 * The actions object defines handlers for different POST requests, usually reacting on a form input.
 */
export const actions = {
    default: async ({cookies, request}) => {
        let form = await request.formData();
        let newLocationName = form.get("location")
        let newLocationLat = form.get("latitude")
        let newLocationLon = form.get("longitude")
        let response = await fetch("http://localhost:8080/locations", {
            method: "POST",
            body: JSON.stringify({
                name: newLocationName,
                latitude: Number.parseFloat(newLocationLat),
                longitude: Number.parseFloat(newLocationLon)
            }),
            headers: {
                "Content-Type": "application/json"
            }
        })
        if (response.status === 201) {
            return { success: true }
        } else {
            return { success: false }
        }
    }
};