function loadCarsPage(){
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
    
}

function allCars(cars){
    let tableInfo = cars.map(cars => `<tr><td>${cars.manufacturer}</td><td>${cars.year}</td><td>${cars.model}</td><td>${cars.price}</td><td>${cars.quantity}</td></tr>`);
                tableInfo.unshift("<table class=\"table\"><tr><th>Manufacturer</th><th>Year</th><th>Model</th><th>Price</th><th>Quantity</th></tr>");
                tableInfo.push("</table>");
                return tableInfo.join('');
}

function createButtons(){
    
}