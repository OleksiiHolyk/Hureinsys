/**
 * Created by Oleksii on 13.09.2016.
 */
$(document).ready(function () {

    $('#clickMe').click(function () {
        alert("Luke, i'm your father");
        $.ajax({
            method: "POST",
            url: "http://localhost:8080/users",
            data: {
                "username": "john",
                "email": "lennon"
            }
        });
    });
});
