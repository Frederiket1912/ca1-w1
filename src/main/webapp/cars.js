function loadCarsPage() {
    //Query the 3 containers.
    let h1content = document.querySelector("#h1content");
    let h3content = document.querySelector("#h3content");
    let content = document.querySelector("#content");
    let linksDiv = document.querySelector("#linksDiv");

    //Clear existing content
    h1content.innerHTML = "";
    h3content.innerHTML = "";
    content.innerHTML = "";
    linksDiv.innerHTML = "";

    //Show all cars
    //let url = "https://frederiket.dk/ca1/api/cars/all";
    let url = "http://localhost:8080/ca1/api/cars/all";
    fetch(url)
            .then(res => res.json())
            .then(data => {
                console.log("data", data);
                content.innerHTML = allCars(data);
            });

    //Add buttons
    createButtons();

}

function allCars(cars) {
    let tableInfo = cars.map(cars => `<tr><td>${cars.manufacturer}</td><td>${cars.year}</td><td>${cars.model}</td><td>${cars.price}</td><td>${cars.quantity}</td></tr>`);
    tableInfo.unshift("<table id=\"carstable\"<table class=\"table\"><tr><th>Manufacturer</th><th>Year</th><th>Model</th><th>Price</th><th>Quantity</th></tr>");
    tableInfo.push("</table>");
    return tableInfo.join('');
}

function createButtons() {
    let linksDiv = document.querySelector("#linksDiv");
    let b1 = "<button type=\"submit\" onclick=\"sortByYearv1()\">Sort by year</button>";
    let b2 = "<br><button type=\"submit\" onclick=\"sortByModelNamev1()\">Sort by model name</button>";
    let b3 = "<br><button type=\"submit\" onclick=\"sortByQuantityv1()\">Sort by Quantity</button>";
    let b4 = "<br><button type=\"submit\" onclick=\"sortByPricev1()\">Sort by Price</button>";
    linksDiv.innerHTML = b1 + b2 + b3 + b4;
}

function sortByYearv1() {
    h1content.innerHTML = "";
    h3content.innerHTML = "";
    content.innerHTML = "";
    //let url = "https://frederiket.dk/ca1/api/cars/all";
    let url = "http://localhost:8080/ca1/api/cars/all";

    fetch(url)
            .then(res => res.json())
            .then(data =>
            {
                content.innerHTML = allCars(data.sort(compareYearv1));
            });
}

function compareYearv1(a, b) {
    const carA = a.year;
    const carB = b.year;
    

    let comparison = 0;
    if (carA > carB) {
        comparison = 1;
    } else if (carA < carB) {
        comparison = -1;
    }
    return comparison;
}

function sortByModelNamev1() {
    h1content.innerHTML = "";
    h3content.innerHTML = "";
    content.innerHTML = "";
    //let url = "https://frederiket.dk/ca1/api/cars/all";
    let url = "http://localhost:8080/ca1/api/cars/all";

    fetch(url)
            .then(res => res.json())
            .then(data =>
            {
                content.innerHTML = allCars(data.sort(compareModelNamev1));
            });
}

function compareModelNamev1(a, b) {
    const carA = a.model.toUpperCase();
    const carB = b.model.toUpperCase();

    let comparison = 0;
    if (carA > carB) {
        comparison = 1;
    } else if (carA < carB) {
        comparison = -1;
    }
    return comparison;
}

function sortByQuantityv1() {
    h1content.innerHTML = "";
    h3content.innerHTML = "";
    content.innerHTML = "";
    //let url = "https://frederiket.dk/ca1/api/cars/all";
    let url = "http://localhost:8080/ca1/api/cars/all";

    fetch(url)
            .then(res => res.json())
            .then(data =>
            {
                content.innerHTML = allCars(data.sort(compareModelNamev1));
            });
}


function compareQuantityv1(a, b) {
    const carA = a.quantity;
    const carB = b.quantity;

    let comparison = 0;
    if (carA > carB) {
        comparison = 1;
    } else if (carA < carB) {
        comparison = -1;
    }
    return comparison;
}

function sortByPricev1() {
    h1content.innerHTML = "";
    h3content.innerHTML = "";
    content.innerHTML = "";
    //let url = "https://frederiket.dk/ca1/api/cars/all";
    let url = "http://localhost:8080/ca1/api/cars/all";

    fetch(url)
            .then(res => res.json())
            .then(data =>
            {
                content.innerHTML = allCars(data.sort(comparePricev1));
            });
}

function comparePricev1(a, b) {
    const carA = a.price;
    const carB = b.price;

    let comparison = 0;
    if (carA > carB) {
        comparison = 1;
    } else if (carA < carB) {
        comparison = -1;
    }
    return comparison;
}

