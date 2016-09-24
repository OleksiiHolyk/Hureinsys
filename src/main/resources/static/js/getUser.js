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
    var r1, r2, r3, r4;
    r1 = $('<div></div>').addClass('phoneNumber').append().append(user.phoneNumber);
    r2 = $('<div></div>').addClass('username').append(user.username);
    r3 = $('<div></div>').addClass('email').append(user.email);
    r4 = $('<div></div>').addClass('password').append(user.password);
    $('<div></div>').addClass('row').append(r1).append(r2).append(r3).append(r4).appendTo(document.getElementById('usersList'));
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
        if($('#userIdInput').val()===''){
            alert("field with ID must not be empty")
        } else
        clearFormFields();
        getUserById();
    });

    $("#butGetUsers").click(function () {
        $('#userIdInput').val('');
        $('#userById').hide();
        clearFormFields();
        getUsers();
    });
});