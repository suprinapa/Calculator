$(document).ready(function (){
    console.log("i am here")
    $(".operators .operatorItem").click(function () {
    // $('.input').val($('.input').val() + $(this).html());
        $('.input').append($(this).html());
    });
    $(".numbers div").click(function () {
     // $('.input').val($('.input').val() + $(this).html());
        $('.input').append($(this).html());
    });
    $("#clear").click(function () {
        $('.input').html(" ");
    });
    $("#backSpace").click(function () {
        var value = $('.input').html();
        var newValue = value.slice(0,-1);
        $('.input').html(newValue);

    });
});

$('#result').click(function() {
    $.ajax({
        url: "http://localhost:8080/calculator",
        type: "POST",
        data: {
            inputExpression: $(".input").html()
        },
        success: function (data) {
            $('.input').html(data.inputExpression)
        },
        error: function (ex) {
            console.log(ex);
            $('.input').html()
        }
    })
    return false;
});
