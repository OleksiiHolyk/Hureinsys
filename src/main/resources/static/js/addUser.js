/**
 * Created by Oleksii on 13.09.2016.
 */
$(document).ready(function () {
    $("#buttonSubmit").click(function () {
        $.ajax({
            url: "/users",
            dataType: "JSON",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                "phoneNumber": $("#phoneNumberInput").val(),
                "username": $("#usernameInput").val(),
                "email": $("#emailInput").val(),
                "password": $("#passwordInput").val()
            })
        }).done(function (msg) {
            console.log(msg);
            alert("User " + msg.username + " is successfully added. User ID="+msg._links.user.href);
        }).fail(function (jqXHR, textStatus) {
            alert("Request failed: " + textStatus);
        });
    });
});