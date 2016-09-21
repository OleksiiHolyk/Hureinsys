/**
 * Created by Oleksii on 13.09.2016.
 */
/*phoneNumber, username, email, password*/

function getUserById() {
    $.ajax({
        method: "GET",
        url: "/users/" + $('#userIdInput').val()
    }).done(function (data) {
        $('#userData').show();
        $('#phoneNumber .val').append(data.phoneNumber);
        $('#username .val').append(data.username);
        $('#email .val').append(data.email);
        $('#password .val').append(data.password);
    }).fail(function (jqXHR, textStatus) {
        alert("Request failed: " + textStatus);
    });
}

function getUsers() {
    $.ajax({
        method: "GET",
        url: "/users"
    }).done(function (data) {
        console.log(data);
        console.log(data._embedded.users);

        var user = data._embedded.users;
        var usersCount = data._embedded.users.length;
        $('#userData').show();


        $.each(user, function (key, value) {
            pasteUsers(user[key]);
        });


    }).fail(function (jqXHR, textStatus) {
        alert("Request failed: " + textStatus);
    });
}

function pasteUsers(user) {
    $('#phoneNumber .val').append(user.phoneNumber).appendTo('.getUsers');
    $('#username .val').append(user.username).appendTo('.getUsers');
    $('#email .val').append(user.email).appendTo('.getUsers');
    $('#password .val').append(user.password).appendTo('.getUsers');
}


$(document).ready(function () {
    $("#butGetUserById").click(function () {
        getUserById();
    });

    $("#butGetUsers").click(function () {
        getUsers();
    });
});
