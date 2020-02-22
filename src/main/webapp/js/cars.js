let url = "https://frederiket.dk/ca1/api/cars/all";
//let url = "http://localhost:8080/ca1/api/cars/all";
let switchbutton = true;

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
    fetch(url)
            .then(res => res.json())
            .then(data => {
                //console.log("data", data);
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
    let b1 = "<button type=\"submit\" onclick=\"sortByYearv2()\">Sort by Year</button>";
    let b2 = "<br><button type=\"submit\" onclick=\"sortByModelNamev2()\">Sort by Model name</button>";
    let b3 = "<br><button type=\"submit\" onclick=\"sortByQuantityv2()\">Sort by Quantity</button>";
    let b4 = "<br><button type=\"submit\" onclick=\"sortByPricev2()\">Sort by Price</button>";
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
    let inputResult = document.getElementById('inputfield').value;
    let dropdown = document.getElementById('dropdown');
    let mathdropdown = document.getElementById('mathsymbols');
    var opt = getSelectedOption(dropdown);
    switch (opt.value) {
        case "manufacturer":
            fetch(url)
                    .then(res => res.json())
                    .then(data => {
                        var filterManufacturer = data.filter(function (data) {
                            return data.manufacturer === inputResult;
                        });
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

function sortByYearv2() {
    var array = [];
    var headers = ["manufacturer", "year", "model", "price", "quantity"];
//    $('#carstable th').each(function (index, item) {
//        headers[index] = $(item).html();
//    });
    $('#carstable tr').has('td').each(function () {
        var arrayItem = {};
        $('td', $(this)).each(function (index, item) {
            arrayItem[headers[index]] = $(item).html();
        });
        array.push(arrayItem);
    });
    switch (switchbutton) {
        case false:
            switchbutton = true;
            content.innerHTML = allCars(array.sort(compareYear));
            break;
        case true:
            switchbutton = false;
            content.innerHTML = allCars(array.sort(compareYear).reverse());
            break;
        default:
            console.log("An error has occured");
            break;
    }
}

function compareYear(a, b) {
    const carA = parseInt(a.year);
    const carB = parseInt(b.year);
    let comparison = 0;
    if (carA > carB) {
        comparison = 1;
    } else if (carA < carB) {
        comparison = -1;
    }
    return comparison;
}

function sortByModelNamev2() {
    var array = [];
    var headers = ["manufacturer", "year", "model", "price", "quantity"];
//    $('#carstable th').each(function (index, item) {
//        headers[index] = $(item).html();
//    });
    $('#carstable tr').has('td').each(function () {
        var arrayItem = {};
        $('td', $(this)).each(function (index, item) {
            arrayItem[headers[index]] = $(item).html();
        });
        array.push(arrayItem);
    });
    switch (switchbutton) {
        case false:
            switchbutton = true;
            content.innerHTML = allCars(array.sort(compareModelName));
            break;
        case true:
            switchbutton = false;
            content.innerHTML = allCars(array.sort(compareModelName).reverse());
            break;
        default:
            console.log("An error has occured");
            break;
    }
}

function compareModelName(a, b) {
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

function sortByQuantityv2() {
    var array = [];
    var headers = ["manufacturer", "year", "model", "price", "quantity"];
//    $('#carstable th').each(function (index, item) {
//        headers[index] = $(item).html();
//    });
    $('#carstable tr').has('td').each(function () {
        var arrayItem = {};
        $('td', $(this)).each(function (index, item) {
            arrayItem[headers[index]] = $(item).html();
        });
        array.push(arrayItem);
    });
    switch (switchbutton) {
        case false:
            switchbutton = true;
            content.innerHTML = allCars(array.sort(compareQuantity));
            break;
        case true:
            switchbutton = false;
            content.innerHTML = allCars(array.sort(compareQuantity).reverse());
            break;
        default:
            console.log("An error has occured");
            break;
    }
}

function compareQuantity(a, b) {
    const carA = parseInt(a.quantity);
    const carB = parseInt(b.quantity);
    let comparison = 0;
    if (carA > carB) {
        comparison = 1;
    } else if (carA < carB) {
        comparison = -1;
    }
    return comparison;
}

function sortByPricev2() {
    var array = [];
    var headers = ["manufacturer", "year", "model", "price", "quantity"];
//    $('#carstable th').each(function (index, item) {
//        headers[index] = $(item).html();
//    });
    $('#carstable tr').has('td').each(function () {
        var arrayItem = {};
        $('td', $(this)).each(function (index, item) {
            arrayItem[headers[index]] = $(item).html();
        });
        array.push(arrayItem);
    });
    switch (switchbutton) {
        case false:
            switchbutton = true;
            content.innerHTML = allCars(array.sort(comparePrice));
            break;
        case true:
            switchbutton = false;
            content.innerHTML = allCars(array.sort(comparePrice).reverse());
            break;
        default:
            console.log("An error has occured");
            break;
    }
}

function comparePrice(a, b) {
    const carA = parseInt(a.price);
    const carB = parseInt(b.price);
    let comparison = 0;
    if (carA > carB) {
        comparison = 1;
    } else if (carA < carB) {
        comparison = -1;
    }
    return comparison;
}

