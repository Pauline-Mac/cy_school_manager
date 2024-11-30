
let classChosen = []

function checkClassList() {

    const container = document.getElementById('class-list');

    const classList = document.getElementsByClassName("class-id-hidden");

    classChosen = [];
    for (let class_ of classList) {
        classChosen.push(class_.value);
    }

    console.log(classChosen);

}


checkClassList();

function addClass(classId, className) {

    if (classChosen.includes(classId))
        return;

    const inputClassId = document.getElementById("add-class-input");
    inputClassId.value = "";

    const container = document.getElementById('class-list');
    const element = document.createElement("span");
    element.classList.add("class-tag");

    const input = document.createElement("input");
    const tag = document.createElement("span");
    const button = document.createElement("span");

    input.type = "hidden";
    input.name = "class[]";
    input.value = classId;
    input.hidden = true;
    input.classList.add("class-id-hidden")

    button.innerText = "X";
    button.classList.add("class-button");

    button.onclick = function () {
        container.removeChild(element);
    }


    tag.innerText = className;
    tag.classList.add("class-id");

    element.appendChild(input);
    element.appendChild(tag);
    element.appendChild(button);


    container.appendChild(element);

    checkClassList();
    clearSuggestions();

}

function onRoleChange() {
    const tr = document.getElementById("group-id-tr");
    const input = document.getElementById("group_id");
    const role = document.getElementById("role");
    const addClass = document.getElementById("add-class");


    switch (role.value) {
        case "professor":
            tr.style.display = "none";
            input.disabled = false;
            addClass.style.display = "none";
            break;
        case "student":
            tr.style.display = "";
            input.disabled = true;
            addClass.style.display = "block";
            break;
        default:
            break;
    }

}


function classSearch() {

    if (document.getElementById("add-class-input").value === "") {
        clearSuggestions();
        return;
    }

    checkClassList();

    var xhr = new XMLHttpRequest();
    var url = "/cy_school_manager/class-search";

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // console.log(xhr.responseText);
            var jsonResponse = JSON.parse(xhr.responseText);

            const classSuggestionDiv = document.getElementById("class-suggestions");

            clearSuggestions();


            for (var class_ of jsonResponse.classes) {
                const newSuggestion = document.createElement("div");

                newSuggestion.classList.add("class-suggestion");
                newSuggestion.id = class_.id;
                newSuggestion.innerText = class_.name;

                newSuggestion.addEventListener("click", function () {
                    addClass(this.id, this.innerText);
                });

                if (!classChosen.includes(class_.id))
                    classSuggestionDiv.appendChild(newSuggestion);
            }




        }

    };

    var className = document.getElementById("add-class-input").value;
    var data = "class=" + encodeURIComponent(className);



    xhr.open("POST", url, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send(data);
}


function clearSuggestions() {
    const classSuggestionDiv = document.getElementById("class-suggestions");

    while (classSuggestionDiv.firstChild) {
        classSuggestionDiv.removeChild(classSuggestionDiv.firstChild);
    }
}

function removeElement(element) {
    element.parentNode.parentNode.removeChild(element.parentNode);
}