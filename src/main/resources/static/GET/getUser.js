/**
 * Created by Oleksii on 13.09.2016.
 */
/*phoneNumber, username, email, password*/
$(document).ready(function () {
    $("#buttonSubmit").click(function () {
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
    });
});
