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
    let url = "https://frederiket.dk/ca1/api/cars/all";
    //let url = "http://localhost:8080/ca1/api/cars/all";
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
    let filter = "<br><button type=\"submit\" onclick=\"loadFilter()\">Filter Table</button>";
    linksDiv.innerHTML = b1 + b2 + b3 + b4 + filter;
}

function loadFilter() {
    let linksDiv = document.querySelector("#linksDiv");
    let option1 = "<select onchange=\"myFunction(event)\" id=\"dropdown\"><option value=\"manufacturer\">Manufacturer</option>";
    let option2 = "<option value=\"year\">Year</option>";
    let option3 = "<option value=\"model\">Model</option>";
    let option4 = "<option value=\"price\">Price</option>";
    let option5 = "<option value=\"quantity\">Quantity</option></select>";
    let optionalmath = "<select id=\"mathsymbols\" name=\"mathsymbols\" style=\"display:none;\">";
    let optionalmath2 = "<option value=\"greaterthan\"> > </option>";
    let optionalmath3 = "<option value=\"smallerthan\"> < </option>";
    let optionalmath4 = "<option value=\"equal\"> = </option> </select>";
    let alloptions = option1 + option2 + option3 + option4 + option5;
    let optional = optionalmath + optionalmath2 + optionalmath3 + optionalmath4;
    let inputfield = "<input type=\"text\" name=\"filtervalue\" id=\"inputfield\" required />";
    let searchbutton = "<button type=\"submit\" onclick=\"searchFilter()\" >Search</button>";
    let searchbar = alloptions + optional + inputfield + searchbutton;
    linksDiv.innerHTML = searchbar;
}

$(document).ready(function () {
    window.myFunction = myFunction;
    function myFunction() {
        var mathsymbols = document.getElementById("mathsymbols").style;
        if (document.getElementById("dropdown").value === "year" || document.getElementById("dropdown").value === "price" || document.getElementById("dropdown").value === "quantity") {
            mathsymbols.display = "";
        } else {
            mathsymbols.display = "none";
        }
    }

});

function searchFilter() {
    let url = "https://frederiket.dk/ca1/api/cars/all";
    //let url = "http://localhost:8080/ca1/api/cars/all";
    let inputResult = document.getElementById('inputfield').value;
    let dropdown = document.getElementById('dropdown');
    let mathdropdown = document.getElementById('mathsymbols');
    var opt = getSelectedOption(dropdown);
    //console.log("dropdown choosen =" + opt.value);
    //console.log("input result = " + inputResult);
    //console.log("Math = " + getSelectedOption(mathdropdown).value);
    switch (opt.value) {
        case "manufacturer":
            fetch(url)
                    .then(res => res.json())
                    .then(data => {
                        var filterManufacturer = data.filter(function (data) {
                            return data.manufacturer === inputResult;
                        });
                        console.log(filterManufacturer);
                        content.innerHTML = allCars(filterManufacturer);
                    });
            break;
        case "year":
            fetch(url)
                    .then(res => res.json())
                    .then(data => {
                        var mathvariable = getSelectedOption(mathdropdown).value;
                        var filterManufacturer = data.filter(function (data) {
                            switch (mathvariable) {
                                case "greaterthan":
                                    return data.year > inputResult;
                                    break;
                                case "smallerthan":
                                    return data.year < inputResult;
                                    break;
                                case "equal":
                                    return data.year === parseInt(inputResult, 10);
                                    break;
                                default:
                                    console.log("An error has occured");
                            }
                        });
                        console.log(filterManufacturer);
                        content.innerHTML = allCars(filterManufacturer);
                    });
            break;
        case "model":
            fetch(url)
                    .then(res => res.json())
                    .then(data => {
                        var filterManufacturer = data.filter(function (data) {
                            return data.model === inputResult;
                        });
                        console.log(filterManufacturer);
                        content.innerHTML = allCars(filterManufacturer);
                    });
            break;
        case "price":
            fetch(url)
                    .then(res => res.json())
                    .then(data => {
                        var mathvariable = getSelectedOption(mathdropdown).value;
                        var filterManufacturer = data.filter(function (data) {
                            switch (mathvariable) {
                                case "greaterthan":
                                    return data.price > inputResult;
                                    break;
                                case "smallerthan":
                                    return data.price < inputResult;
                                    break;
                                case "equal":
                                    return data.price === parseInt(inputResult, 10);
                                    break;
                                default:
                                    console.log("An error has occured");
                            }
                        });
                        console.log(filterManufacturer);
                        content.innerHTML = allCars(filterManufacturer);
                    });
            break;
        case "quantity":
            fetch(url)
                    .then(res => res.json())
                    .then(data => {
                        var mathvariable = getSelectedOption(mathdropdown).value;
                        var filterManufacturer = data.filter(function (data) {
                            switch (mathvariable) {
                                case "greaterthan":
                                    return data.quantity > inputResult;
                                    break;
                                case "smallerthan":
                                    return data.quantity < inputResult;
                                    break;
                                case "equal":
                                    return data.quantity === parseInt(inputResult, 10);
                                    break;
                                default:
                                    console.log("An error has occured");
                            }
                        });
                        console.log(filterManufacturer);
                        content.innerHTML = allCars(filterManufacturer);
                    });
            break;
        default:
            console.log("An error has occured");
    }
}

function getSelectedOption(option) {
    var opt;
    for (var i = 0, len = option.options.length; i < len; i++) {
        opt = option.options[i];
        if (opt.selected === true) {
            break;
        }
    }
    return opt;
}


function sortByYearv1() {
    h1content.innerHTML = "";
    h3content.innerHTML = "";
    content.innerHTML = "";
    let url = "https://frederiket.dk/ca1/api/cars/all";
    //let url = "http://localhost:8080/ca1/api/cars/all";
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
    let url = "https://frederiket.dk/ca1/api/cars/all";
    //let url = "http://localhost:8080/ca1/api/cars/all";
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
    let url = "https://frederiket.dk/ca1/api/cars/all";
    //let url = "http://localhost:8080/ca1/api/cars/all";
    fetch(url)
            .then(res => res.json())
            .then(data =>
            {
                content.innerHTML = allCars(data.sort(compareQuantityv1));
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
    let url = "https://frederiket.dk/ca1/api/cars/all";
    //let url = "http://localhost:8080/ca1/api/cars/all";
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

