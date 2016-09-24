/**
 * Created by Oleksii on 13.09.2016.
 */
/*phoneNumber, username, email, password*/

function getUserById() {
    $.ajax({
        method: "GET",
        url: "/users/" + $('#userIdInput').val()
    }).done(function (data) {
        $('#userById').show();
        $('#phoneNumber').find('.val').append(data.phoneNumber);
        $('#username').find('.val').append(data.username);
        $('#email').find('.val').append(data.email);
        $('#password').find('.val').append(data.password);
    }).fail(function (jqXHR, textStatus) {
        alert("Request failed: " + textStatus);
    });
}

function getUsers() {
    $.ajax({
        method: "GET",
        url: "/users"
    }).done(function (data) {
        $('#usersList').show();
        var user = data._embedded.users;
        $.each(user, function (key, value) {
            pasteUsers(user[key]);
        });
    }).fail(function (jqXHR, textStatus) {
        alert("Request failed: " + textStatus);
    });
}

function pasteUsers(user) {
    var row1, row2, row3, row4;
    row1 = $('<div></div>').addClass('phoneNumber');
    $('<span></span>').addClass('fld').append("Phone number: ").appendTo(row1);
    $('<span></span>').addClass('val').append(user.phoneNumber).appendTo(row1);

    row2 = $('<div></div>').addClass('username');
    $('<span></span>').addClass('fld').append("Username: ").appendTo(row2);
    $('<span></span>').addClass('val').append(user.username).appendTo(row2);

    row3 = $('<div></div>').addClass('email');
    $('<span></span>').addClass('fld').append("Email: ").appendTo(row3);
    $('<span></span>').addClass('val').append(user.email).appendTo(row3);

    row4 = $('<div></div>').addClass('password');
    $('<span></span>').addClass('fld').append("Password: ").appendTo(row4);
    $('<span></span>').addClass('val').append(user.password).appendTo(row4);

    $('<div></div>').addClass('row')
        .append(row1).append(row2).append(row3).append(row4)
        .appendTo(document.getElementById('usersList'));
}

function clearFormFields() {
    $('#phoneNumber').find('.val').html('');
    $('#username').find('.val').html('');
    $('#email').find('.val').html('');
    $('#password').find('.val').html('');
    $('#usersList').html('');
}

$(document).ready(function () {
    $("#butGetUserById").click(function () {
        if ($('#userIdInput').val() === '') {
            alert("field with ID must not be empty")
        } else {
            clearFormFields();
            getUserById();
        }
    });

    $("#butGetUsers").click(function () {
        $('#userIdInput').val('');
        $('#userById').hide();
        clearFormFields();
        getUsers();
    });
});