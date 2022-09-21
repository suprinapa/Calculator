<%--
  Created by IntelliJ IDEA.
  User: supri
  Date: 8/29/2022
  Time: 9:52 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <style>
        <%@include file="/WEB-INF/css/my_style.css" %>
    </style>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.12.1/css/jquery.dataTables.min.css"/>
    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
</head>
<body>
<div class="calculator">
    <div>
        <a type="button" href="/history/1"><i class="fa fa-history"></i></a>
        <a type="button" href="/formula/addFormula">add Formula</a>
        <button class="btn_custom btn_custom-primary" onclick="popUP()"><i class="fa-solid fa-calculator"></i></button>
        <%--    <a href='#' onclick='javascript:window.open("http://localhost:8080/formula", "_blank", "scrollbars=1,resizable=1,height=300,width=450");' title='Pop Up'>My Popup</a>--%>
        <div id="popup-container">
            <%--            <%@include file='formula.jsp' %>--%>
            <table id="tableFormula" class="display" style="width:100%">
                <thead>
                <tr>
                    <th>formula</th>
                    <th>action</th>
                </tr>
                </thead>
            </table>
            <div id="popup-close-container">
                <span id="popup-close">Close</span>
            </div>
        </div>

        <br>
        <%--    <form:input class ="input" path="inputExpression"/>--%>
        <div class="input" id="input">
        </div>
        <div class="buttons">
            <div class="operators">
                <div class="operatorItem">+</div>
                <div class="operatorItem">-</div>
                <div class="operatorItem">*</div>
                <div class="operatorItem">/</div>
                <div class="operatorItem">!</div>
                <div class="operatorItem">^</div>
                <div id="backSpace"><i class="fas fa-backspace"></i></div>
            </div>
            <div class="leftPanel">
                <div class="numbers">
                    <div>7</div>
                    <div>8</div>
                    <div>9</div>
                </div>
                <div class="numbers">
                    <div>4</div>
                    <div>5</div>
                    <div>6</div>
                </div>
                <div class="numbers">
                    <div>1</div>
                    <div>2</div>
                    <div>3</div>
                </div>
                <div class="numbers">
                    <div>0</div>
                    <div>.</div>
                    <div id="clear"><i class="fas fa-eraser"></i></div>
                </div>
            </div>
            <div class="equal" id="result"><i class="fas fa-equals"></i></div>
            <%--        <input class = "equal" id="result" type="submit" value="=">--%>
        </div>
    </div>
</div>
<script>
        function CurrentState(){
        this.currentFormula = null;

        this.setFormula = function(formula){
        this.currentFormula = formula;
    }

        this.getFormula = function(){
        return this.currentFormula;
    }
    }
        let FORMULA_MODE = false;
        let currentState = new CurrentState();
        // let flag = true;
        let currentPosition=0;
        let variableArray = [];
        function popUP() {
        $("#popup-container").show(function () {
            let table = $('#tableFormula').DataTable({
                ajax: "/formula",
                paging: false,
                searching: false,
                ordering: false,
                info: false,
                retrieve: true,
                columns: [
                    {data: "formula"},
                    {data: null},
                ],
                columnDefs: [
                    {
                        targets: -1,
                        defaultContent: '<button onclick="useMe()">Use Me</button>',
                    },
                ],
            });
            $('#tableFormula tbody').off()
                .on('click', 'button', function () {
                    let data = table.row($(this).parents('tr')).data();
                    $("#popup-container").hide();

                    var operatorArray = [];

                    var regexArray = new RegExp("(?<=[-+/^*!()])|(?=[-+/^*!()])")
                    var expressionArray =
                        data.formula.split(regexArray);
                    currentState.setFormula(expressionArray);
                    expressionArray.forEach(value => {
                        if (!isOperator(value)) {
                            variableArray.push(value);
                        } else {
                            operatorArray.push(value);
                        }
                    })
                    $(".input")[0].innerHTML = '';
                    for (let input = 0; input <= variableArray.length - 1; input++) {
                        $(".input").append(variableArray[input] + "=" + "<div class='textArea' id=" + input + "></div>" + '<br>')
                    }
                })
        })
        $("#popup-close").click(function () {
        $("#popup-container").hide();
    })
    }

        function isOperator(operator) {
        if (operator === "*" || operator === "+" || operator === "-" || operator === "/" || operator === "^"
        || operator === "!") {
        return true;
    }
        return false;
    }
    <%@include file="/WEB-INF/jsp/my_script.js" %>
</script>
</body>
</html>
