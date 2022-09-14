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
    <link
            rel="stylesheet"
            type="text/css"
            href="https://cdn.datatables.net/1.12.1/css/jquery.dataTables.min.css"
    />
    <link rel="stylesheet" type="text/css"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css"/>
</head>
<body>
<div class="calculator">
    <div>
        <a type="button" href="/history/1"><i class="fa fa-history"></i></a>
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
        <script>
            let editor;
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
                            defaultContent: '<button>Use Me</button>',
                        },
                    ],
                });
                    $('#tableFormula tbody').on('click', 'button', function () {
                        var data = table.row($(this).parents('tr')).data();
                        console.log(data);
                    });
                    // $.ajax({
                    //     url: "http://localhost:8080/formula",
                    //     type: "GET",
                    //     success: function (data) {
                    //         allFormulaList = data
                    //         console.log(allFormulaList)
                    //              $("#popup-container").html(allFormulaList);
                    //              $("#popup-container").show();
                    //     },
                    //     error: function (ex) {
                    //         console.log(ex);
                    //     }
                    // })
                    $("#popup-close").click(function () {
                        $("#popup-container").hide();
                    })

                })
            }
        </script>
        <br>
        <%--    <form:input class ="input" path="inputExpression"/>--%>
        <div class="input" id="input"></div>
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
    <%@include file="/WEB-INF/jsp/my_script.js" %>
</script>
</body>
</html>
