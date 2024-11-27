
function addClass() {

    const inputClassId = document.getElementById("add-class-input");
    const classId = inputClassId.value;
    inputClassId.value = "";

    if (classId.length > 0) {

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

        button.innerText = "X";
        button.classList.add("class-button");

        button.onclick = function () {
            container.removeChild(element);
        }


        tag.innerText = classId;
        tag.classList.add("class-id");

        element.appendChild(input);
        element.appendChild(tag);
        element.appendChild(button);


        container.appendChild(element);
    }

}

function onRoleChange() {
    const tr = document.getElementById("group-id-tr");
    const input = document.getElementById("group_id");
    const role = document.getElementById("role");


    switch (role.value) {
        case "professor":
            tr.style.display = "none";
            input.disabled = false;
            break;
        case "student":
            tr.style.display = "";
            input.disabled = true;
            break;
        default:
            break;
    }

}
