$(async function () {
    await getTableWithUsers();
    await showLoggedInUser();
    await showRolesInNewUserForm()
});

const userFetchService = {
    head: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Referer': null
    },
    findAllUsers: async () => await fetch('api/users'),
    findAllRoles: async () => await fetch('api/users/roles'),
    showSingleUser: async (id) => await fetch(`api/users/${id}`),
    findOneUser: async (id) => await fetch(`api/users/${id}`),
    addNewUser: async (user) => await fetch('api/users', {
        credentials: 'include',
        method: 'POST',
        headers: userFetchService.head,
        body: JSON.stringify(user)
    }),
    updateUserById: async (user) => await fetch(`api/users`, {
        credentials: 'include',
        method: 'PATCH',
        headers: userFetchService.head,
        body: JSON.stringify(user)
    }),
    deleteUser: async (id) => await fetch(`api/users/${id}`, {credentials: 'include', method: 'DELETE', headers: userFetchService.head})
}

function getTableWithUsers() {
    let table = $('#AllUsersTable');
    userFetchService.findAllUsers()
        .then(res => res.json())
        .then(users => {
            table.empty();
            users.forEach(user => {
                let tableFilling = `$(
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.username}</td>
                            <td>${user.name}</td>
                            <td>${user.surname}</td>
                            <td>${user.department}</td>
                            <td>${user.salary}</td>
                            <td>${user.roles.map(role => " " + role.nameRole.substring(5))}</td>
                            <td>
                                <button type="button" onclick="editUser(${user.id})" data-action="edit" class="btn btn-info"
                                data-toggle="modal" data-target="#edit">Edit</button>
                            </td>
                            <td>
                                <button type="button" onclick="deleteUser(${user.id})" data-action="delete" class="btn btn-danger"
                                data-toggle="modal" data-target="#delete">Delete</button>
                            </td>
                        </tr>
                )`;
                table.append(tableFilling);
            })
        })
}

function showLoggedInUser() {
    userFetchService.showSingleUser(headUserIdFromHtml)
        .then(res => res.json())
        .then(user => {
            $('#headUserTableInfo').append(`
            <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.name}</td>
                    <td>${user.surname}</td>
                    <td>${user.department}</td>
                    <td>${user.salary}</td>
                    <td>${user.roles.map(role => " " + role.nameRole.substring(5))}</td>
                </tr>           
            `);

        })
}

function editUser(id) {
    userFetchService.findOneUser(id)
        .then(res => {
            res.json().then(user => {

                $('#editId').val(user.id)
                $('#editUsername').val(user.username)
                $('#editPassword').val(user.password)
                $('#editName').val(user.name)
                $('#editSurname').val(user.surname)
                $('#editDepartment').val(user.department)
                $('#editSalary').val(user.salary)
                $('#editRoles').empty();

                userFetchService.findAllRoles()
                    .then(res => res.json())
                    .then(roles => {
                        roles.forEach(role => {
                            let selectedRole = false;
                            for (let i = 0; i < user.roles.length; i++) {
                                if (user.roles[i].nameRole === role.nameRole) {
                                    selectedRole = true;
                                    break;
                                }
                            }
                            let el = document.createElement("option");
                            el.text = role.nameRole.substring(5);
                            el.value = role.id;
                            if (selectedRole) el.selected = true;
                            $('#editRoles')[0].appendChild(el);
                        })
                    })
            })
        })
}

document.forms["editForm"].addEventListener("submit", ev => {
    ev.preventDefault();
    let editUserRoles = [];
    for (let i = 0; i < document.forms["editForm"].roles.options.length; i++) {
        if (document.forms["editForm"].roles.options[i].selected) editUserRoles.push({
            id: document.forms["editForm"].roles.options[i].value,
            nameRole: 'ROLE_' + document.forms["editForm"].roles.options[i].text
        })
    }
    let user = {
        id: document.getElementById('editId').value,
        userAlias: document.getElementById('editUsername').value,
        password: document.getElementById('editPassword').value,
        name: document.getElementById('editName').value,
        surname: document.getElementById('editSurname').value,
        department: document.getElementById('editDepartment').value,
        salary: document.getElementById('editSalary').value,
        roles: editUserRoles
    };
    userFetchService.updateUserById(user).then(() => {
        $('#editModalCloseButton').click();
        getTableWithUsers()
    })

})

function deleteUser(id) {
    userFetchService.findOneUser(id)
        .then(res => {
            res.json().then(user => {
                $('#deleteId').val(user.id)
                $('#deleteUsername').val(user.username)
                $('#deletePassword').val(user.password)
                $('#deleteName').val(user.name)
                $('#deleteSurname').val(user.surname)
                $('#deleteDepartment').val(user.department)
                $('#deleteSalary').val(user.salary)
                $('#deleteRoles').empty();

                userFetchService.findAllRoles()
                    .then(res => res.json())
                    .then(roles => {
                        roles.forEach(role => {
                            let selectedRole = false;
                            for (let i = 0; i < user.roles.length; i++) {
                                if (user.roles[i].nameRole === role.nameRole) {
                                    selectedRole = true;
                                    break;
                                }
                            }
                            let el = document.createElement("option");
                            el.text = role.nameRole.substring(5);
                            el.value = role.id;
                            if (selectedRole) el.selected = true;
                            $('#deleteRoles')[0].appendChild(el);
                        })
                    });
            })
        })
}

function deleteUserById() {
    let id = document.getElementById('deleteId').value;
    document.forms["deleteForm"].addEventListener("submit", ev => {
        ev.preventDefault();
        userFetchService.deleteUser(id)
            .then(() => {
                getTableWithUsers()
                $('#deleteModalCloseButton').click();
            })
    })
}


document.forms["newUserForm"].addEventListener("submit", ev => {
    ev.preventDefault();
    let newUserRoles = [];
    for (let i = 0; i < document.forms["newUserForm"].roles.options.length; i++) {
        if (document.forms["newUserForm"].roles.options[i].selected) newUserRoles.push({
            id: document.forms["newUserForm"].roles.options[i].value,
            name: 'ROLE_' + document.forms["newUserForm"].roles.options[i].text
        })
    }
    let user = {
        userAlias: document.getElementById('addUsername').value,
        password: document.getElementById('addPassword').value,
        name: document.getElementById('addName').value,
        surname: document.getElementById('addSurname').value,
        department: document.getElementById('addDepartment').value,
        salary: document.getElementById('addSalary').value,
        roles: newUserRoles
    };
    userFetchService.addNewUser(user)
        .then(() => {
            document.forms["newUserForm"].reset();
            getTableWithUsers();
            $('#nav-tab li:first-child a').tab('show')
        })
})

function showRolesInNewUserForm() {
    userFetchService.findAllRoles()
        .then(res => res.json())
        .then(roles => {
            roles.forEach(role => {
                let el = document.createElement("option");
                el.text = role.nameRole.substring(5);
                el.value = role.id;
                $('#addRoles')[0].appendChild(el);
            })
        })
}