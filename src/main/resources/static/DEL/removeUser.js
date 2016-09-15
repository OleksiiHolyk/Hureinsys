/**
 * Created by Oleksii on 13.09.2016.
 */

$(document).ready(function () {
    $("#buttonSubmit").click(function () {
        $.ajax({
            method: "DELETE",
            url: "/users/" + $('#userIdInput').val()
        }).done(function () {
            alert("User successfully deleted.")
        }).fail(function (jqXHR, textStatus) {
            alert("Request failed: " + textStatus);
        });
    });
});
