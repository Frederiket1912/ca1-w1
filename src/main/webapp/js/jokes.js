let url2 = "https://frederiket.dk/ca1/api/jokes/all";
//let url3 = "http://localhost:8080/ca1/api/jokes/all";
let alljokes;

function loadJokesPage() {
    //Query the 3 containers.
    let h1content = document.querySelector("#h1content");
    let h3content = document.querySelector("#h3content");
    let content = document.querySelector("#content");
    let linksDivs = document.querySelector("#linksDiv");

    //Clear existing content
    h1content.innerHTML = "";
    h3content.innerHTML = "";
    content.innerHTML = "";
    linksDivs.innerHTML = "";

    //Show all jokes
    fetch(url2)
            .then(res => res.json())
            .then(data => {
                alljokes = data;
                content.innerHTML = allJokes(data);
            });

    //Add buttons
    createButtons2();

}

function allJokes(jokes) {
    let tableInfo = jokes.map(jokes => `<tr><td>${jokes.joke}</td></tr>`);
    tableInfo.unshift("<table id=\"jokestable\"<table class=\"table\"><tr><th>Joke</th></tr>");
    tableInfo.push("</table>");
    return tableInfo.join('');
}

function createButtons2() {
    let linksDivs = document.querySelector("#linksDiv");
    let b1 = "<button type=\"submit\" onclick=\"randomJoke()\">Random Joke</button>";
    let filter2 = "<br><button type=\"submit\" onclick=\"loadSearch()\">Search by ID</button>";
    linksDivs.innerHTML = b1 + filter2;
}

function randomJoke(joke) {
    let arraySize = alljokes.length;
    let randomNum = Math.floor(Math.random() * arraySize);
    let arrayPick = alljokes[randomNum];

    console.log(arrayPick);
    let randomTable = "<table id=\"jokestable\"<table class=\"table\"><tr><th>Joke</th></tr>" +
            `<tr><td>${arrayPick.joke}</td></tr>`
            + "</table>";
    content.innerHTML = randomTable;
}

function loadSearch() {
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