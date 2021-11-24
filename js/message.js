
function traerInformacionMessages(action) {
    $.ajax({
        url: "https://g8ad3f4f39a8940-dbreto1.adb.ca-toronto-1.oraclecloudapps.com/ords/admin/message/message",
        type: "GET",
        datatype: "JSON",
        success: function (respuesta) {
            console.log(respuesta);
            $("#resultadoMessages").empty("");
            pintarRespuestaMessages(respuesta.items);
        }
    });    
}

function pintarRespuestaMessages(items) {
    let myTable = "<table class='dataTable'>";
    for (i = 0; i < items.length; i++) {
        myTable += "<tr>";
        myTable += "<td>" + items[i].id + "</td>";
        myTable += "<td>" + items[i].messagetext + "</td>";
        myTable += "<td> <button onclick='borrarElementoMessages(" + items[i].id + ")'>Borrar</button></td>";
        myTable += "<td> <button onclick='editarInformacionMessages("+ items[i].id + ", \"" + items[i].messagetext + "\", "+")'>Editar</button></td>";
        myTable += "</tr>";
    }
    myTable += "</table>";
    $("#resultadoMessages").append(myTable);
}


function borrarElementoMessages(idElemento) {
    let myData={
        id:idElemento
    }
    let dataToSend = JSON.stringify(myData)
    $.ajax({
        url: "https://g8ad3f4f39a8940-dbreto1.adb.ca-toronto-1.oraclecloudapps.com/ords/admin/message/message",
        type: "DELETE",
        data: dataToSend,
        contentType: "application/JSON",
        datatype: "JSON",
        success: function (respuesta) {
            $("#resultadoMessages").empty("");
            traerInformacionMessages(0);
            console.log("Se ha eliminado");
        }
    });
}

function guardarInformacionMessages() {
    $.ajax({
        url: "https://g8ad3f4f39a8940-dbreto1.adb.ca-toronto-1.oraclecloudapps.com/ords/admin/message/message",
        type: "GET",
        datatype: "JSON",
        success: function (datos) {
            console.log(datos);
            let mayor = 0;
            for (i = 0; i < datos.items.length; i++) {
                if (datos.items[i].id > mayor){
                    mayor = datos.items[i].id;
                }
            }
            setTimeout(() => {
                let idAAsignar = mayor + 1;
                let myData = {
                    id: idAAsignar,
                    messagetext: $("#messageText").val()
                }
                $.ajax({
                    url: "https://g8ad3f4f39a8940-dbreto1.adb.ca-toronto-1.oraclecloudapps.com/ords/admin/message/message",
                    type: "POST",
                    data: myData,
                    datatype: "JSON",
                    success: function (respuesta) {
                        $("#resultadoMessages").empty("");
                        $("#messageText").val("");
                        traerInformacionMessages(0);
                        console.log("Se ha guardado!")
                    }
                });
            }, 1000);
        }
    });
    
}

function editarInformacionMessages(id, messagetext) {
    $("#messageText").val(messagetext);
    let botonDeActualizar = document.getElementById("actualizarMessages");
    botonDeActualizar.removeAttribute("disabled");
    let value = "actualizarInformacionMessages("+id+")";
    botonDeActualizar.setAttribute("onclick", value);
}

function actualizarInformacionMessages(idElemento) {
    let myData={
        id:idElemento,
        messagetext:$("#messageText").val(),
    }
    let dataToSend = JSON.stringify(myData)
    $.ajax({
        url: "https://g8ad3f4f39a8940-dbreto1.adb.ca-toronto-1.oraclecloudapps.com/ords/admin/message/message",
        type: "PUT",
        data: dataToSend,
        contentType: "application/JSON",
        datatype: "JSON",
        success: function (respuesta) {
            $("#resultadoMessages").empty("");
            $("#messageText").val("");
            traerInformacionMessages(0);
            let botonDeActualizar = document.getElementById("actualizarMessages");
            botonDeActualizar.removeAttribute("onclick");
            botonDeActualizar.setAttribute("disabled", "");
            console.log("Se ha actualizado!")
        }
    });
}