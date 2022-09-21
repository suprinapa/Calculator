$(document).ready(function () {
    $(".operators .operatorItem").click(function () {
        // $('.input').val($('.input').val() + $(this).html());
        $('.input').append($(this).html());
    });
    $(".numbers div").click(function () {
        if (FORMULA_MODE) {
            $('#' + currentPosition).append($(this).html());

        } else {
            $('.input').append($(this).html());
        }
        // $('.input').val($('.input').val() + $(this).html());
    });
    $("#clear").click(function () {
        FORMULA_MODE = true;
        $('.input').html(" ");
    });
    $("#backSpace").click(function () {
        var value = $('.input').html();
        var newValue = value.slice(0, -1);
        $('.input').html(newValue);
    });
});

$('#result').click(function () {
    if (FORMULA_MODE){
        currentPosition++
        let finalExpression = [];
        if (currentPosition >= variableArray.length - 1) {
            const map = new Map();
            $(".textArea").each(function (index) {
                const text = $(this).text();
                map.set(variableArray[index], text)
            })
            currentState.getFormula().forEach(input => {
                if (!isOperator(input)) {
                    finalExpression.push(map.get(input))
                } else {
                    finalExpression.push(input)
                }
            })
            console.log(finalExpression)
            var finalStringExpression = finalExpression.join("");
            console.log(finalStringExpression);
        }
    }
    let data = {
        inputExpression: null
    };
    if (FORMULA_MODE) {
        data.inputExpression = finalStringExpression;
    } else {
        data.inputExpression = $(".input").html();
    }
    $.ajax({
        url: "http://localhost:8080/calculator",
        type: "POST",
        data: data,
        success: function (data) {
            console.log(data)
            $('.input').html(data.inputExpression)
        },
        error: function (ex) {
            console.log(ex);
            $('.input').html()
        }
    })
    return false;
});

function useMe() {
    FORMULA_MODE = true;
    variableArray = [];
    currentPosition = 0;
    // flag = false;
}
