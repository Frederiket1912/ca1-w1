//let url2 = "https://frederiket.dk/ca1/api/jokes/all";
let url2 = "http://localhost:8080/ca1/api/jokes/all";
let switchbutton = true;
let alljokes;

function loadJokesPage() {
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

    //Show all jokes
    fetch(url2)
            .then(res => res.json())
            .then(data => {
                alljokes = data;
                content.innerHTML = allJokes(data);
            });

    //Add buttons
    createButtons();

}

function allJokes(jokes) {
    let tableInfo = jokes.map(jokes => `<tr><td>${jokes.id}</td><td>${jokes.joke}</td></tr>`);
    tableInfo.unshift("<table id=\"jokestable\"<table class=\"table\"><tr><th>ID</th><th>Joke</th></tr>");
    tableInfo.push("</table>");
    return tableInfo.join('');
}

function createButtons() {
    let linksDiv = document.querySelector("#linksDiv");
    let b1 = "<button type=\"submit\" onclick=\"getRandomJoke()\">Random Joke</button>";
    let filter = "<br><button type=\"submit\" onclick=\"loadFilter()\">Search by ID</button>";
    linksDiv.innerHTML = b1 + filter;
}

function loadFilter() {
    let linksDiv = document.querySelector("#linksDiv");
    let inputfield = "<input type=\"number\" name=\"fieldvalue\" id=\"field\" required />";
    let searchbutton = "<button type=\"submit\" onclick=\"searchFilter()\" >Search</button>";
    let searchbar = inputfield + searchbutton;
    linksDiv.innerHTML = searchbar;
}

//function searchFilter() {
//    let id = document.getElementById('inputfield').value;
//        default:
//            console.log("An error has occured");
//    }
//}

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