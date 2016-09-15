/**
 * Created by Oleksii on 13.09.2016.
 */
$(document).ready(function () {
    $.ajax({
        url: "/users",
        dataType: "JSON",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            "username": "john",
            "email": "lennon"
        })
    });
});
