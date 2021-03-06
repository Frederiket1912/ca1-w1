//Constants for all DOM elements im manipulating
const HEROPAGE = document.querySelector("#heroPage");
const CONTENT = document.querySelector("#content");



function heroesToTable(heroes) {
    var tableinfo = heroes.map(x => `<tr><td>  ${x.heroName} </td><td> ${x.intelligence} </td><td> ${x.strength} </td>
    <td>  ${x.speed} </td><td> ${x.durability} </td><td> ${x.power} </td>
    <td>  ${x.combat} </td><td> ${x.publisher} </td></tr>`);

    tableinfo.unshift("<table id=\"indextable\" class=\"table\">\n\
    <tr><th onclick=\"sortByLetters(0)\">Hero Name</th>\n\
    <th onclick=\"sortByNumbers(1)\">Intelligence</th>\n\
    <th onclick=\"sortByNumbers(2)\">Strength</th>\n\
    <th onclick=\"sortByNumbers(3)\">Speed</th>\n\
    <th onclick=\"sortByNumbers(4)\">Durability</th>\n\
    <th onclick=\"sortByNumbers(5)\">Power</th>\n\
    <th onclick=\"sortByNumbers(6)\">Combat</th>\n\
    <th onclick=\"sortByLetters(7)\">Publisher</th></tr>");

    tableinfo.push("</table>");
    return tableinfo.join('');
}

function makeHeroContent(e) {
    e.preventDefault();
    let url = "/ca1/api/hero/all";
    let h1content = document.querySelector("#h1content");
    let h3content = document.querySelector("#h3content");
    let linksDiv = document.querySelector("#linksDiv");
    //Clear existing content
    h1content.innerHTML = "";
    h3content.innerHTML = "";
    CONTENT.innerHTML = "";
    linksDiv.innerHTML = "";
    fetch(url)
            .then(res => res.json()) //in flow1, just do it
            .then(data => {
                // Inside this callback, and only here, the response data is available
                console.log("data", data);
                CONTENT.innerHTML = heroesToTable(data);               
                /* data now contains the response, converted to JavaScript
                 Observe the output from the log-output above
                 Now, just build your DOM changes using the data*/
            });
}

function sortByLetters(n) {
    var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
    table = document.getElementById("indextable");
    switching = true;
    // Set the sorting direction to ascending:
    dir = "asc";
    /* Make a loop that will continue until
     no switching has been done: */
    while (switching) {
        // Start by saying: no switching is done:
        switching = false;
        rows = table.rows;
        /* Loop through all table rows (except the
         first, which contains table headers): */
        for (i = 1; i < (rows.length - 1); i++) {
            // Start by saying there should be no switching:
            shouldSwitch = false;
            /* Get the two elements you want to compare,
             one from current row and one from the next: */
            x = rows[i].getElementsByTagName("TD")[n];
            y = rows[i + 1].getElementsByTagName("TD")[n];
            /* Check if the two rows should switch place,
             based on the direction, asc or desc: */
            if (dir == "asc") {
                if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                    // If so, mark as a switch and break the loop:
                    shouldSwitch = true;
                    break;
                }
            } else if (dir == "desc") {
                if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                    // If so, mark as a switch and break the loop:
                    shouldSwitch = true;
                    break;
                }
            }
        }
        if (shouldSwitch) {
            /* If a switch has been marked, make the switch
             and mark that a switch has been done: */
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
            // Each time a switch is done, increase this count by 1:
            switchcount++;
        } else {
            /* If no switching has been done AND the direction is "asc",
             set the direction to "desc" and run the while loop again. */
            if (switchcount == 0 && dir == "asc") {
                dir = "desc";
                switching = true;
            }
        }
    }
}

function sortByNumbers(n) {
    var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
    table = document.getElementById("indextable");
    switching = true;
    // Set the sorting direction to ascending:
    dir = "asc";
    /* Make a loop that will continue until
     no switching has been done: */
    while (switching) {
        // Start by saying: no switching is done:
        switching = false;
        rows = table.rows;
        /* Loop through all table rows (except the
         first, which contains table headers): */
        for (i = 1; i < (rows.length - 1); i++) {
            // Start by saying there should be no switching:
            shouldSwitch = false;
            /* Get the two elements you want to compare,
             one from current row and one from the next: */
            x = rows[i].getElementsByTagName("TD")[n];
            y = rows[i + 1].getElementsByTagName("TD")[n];
            /* Check if the two rows should switch place,
             based on the direction, asc or desc: */
            if (dir == "asc") {
                if (Number(x.innerHTML) > Number(y.innerHTML)) {
                    // If so, mark as a switch and break the loop:
                    shouldSwitch = true;
                    break;
                }
            } else if (dir == "desc") {
                if (Number(x.innerHTML) < Number(y.innerHTML)) {
                    // If so, mark as a switch and break the loop:
                    shouldSwitch = true;
                    break;
                }
            }
        }
        if (shouldSwitch) {
            /* If a switch has been marked, make the switch
             and mark that a switch has been done: */
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
            // Each time a switch is done, increase this count by 1:
            switchcount++;
        } else {
            /* If no switching has been done AND the direction is "asc",
             set the direction to "desc" and run the while loop again. */
            if (switchcount == 0 && dir == "asc") {
                dir = "desc";
                switching = true;
            }
        }
    }
}

//Eventlisteners

HEROPAGE.addEventListener("click", makeHeroContent);



