function traerInformacionTools() {
    $.ajax({
        url: "http://132.145.103.198:8080/api/Tool/all",
        type: "GET",
        datatype: "JSON",
        success: function (respuesta) {
            console.log(respuesta);
            $("#resultadoTools").empty("");
            pintarRespuestaTools(respuesta);
        }
    });
}

function pintarRespuestaTools(items) {
    let myTable = "<table class='dataTable'>";
    for (i = 0; i < items.length; i++) {
        myTable += "<tr>";
        myTable += "<td>" + items[i].id + "</td>";
        myTable += "<td>" + items[i].name + "</td>";
        myTable += "<td>" + items[i].description + "</td>";
        myTable += "<td>" + items[i].brand + "</td>";
        myTable += "<td>" + items[i].year + "</td>";
        myTable += "<td>" + items[i].category.name + "</td>";
        myTable += "</tr>";
    }
    myTable += "</table>";
    $("#resultadoTools").append(myTable);
}

function guardarInformacionTools() {
    $.ajax({
        url: "http://132.145.103.198:8080/api/Tool/all",
        type: "GET",
        datatype: "JSON",
        success: function (datos) {
            console.log(datos);
            let myData = {
                id: idAAsignar,
                brand: $("#brandTools").val(),
                model: $("#modelTools").val(),
                category_id: 1,
                name: $("#nameTools").val(),
            }
            $.ajax({
                url: "http://132.145.103.198:8080/api/Tool/save",
                type: "POST",
                data: myData,
                datatype: "JSON",
                success: function (respuesta) {
                    $("#resultadoTools").empty("");
                    $("#brandTools").val("");
                    $("#modelTools").val("");
                    $("#nameTools").val("");
                    traerInformacionTools();
                    console.log("Se ha guardado!")
                }
            });
        }
    });
    
}