
function traerInformacionClients(action) {
    $.ajax({
        url: "https://g8ad3f4f39a8940-dbreto1.adb.ca-toronto-1.oraclecloudapps.com/ords/admin/client/client",
        type: "GET",
        datatype: "JSON",
        success: function (respuesta) {
            console.log(respuesta);
            $("#resultadoClients").empty("");
            pintarRespuestaClients(respuesta.items);
        }
    });    
}

function pintarRespuestaClients(items) {
    let myTable = "<table class='dataTable'>";
    for (i = 0; i < items.length; i++) {
        myTable += "<tr>";
        myTable += "<td>" + items[i].id + "</td>";
        myTable += "<td>" + items[i].name + "</td>";
        myTable += "<td>" + items[i].email + "</td>";
        myTable += "<td>" + items[i].age + "</td>";
        myTable += "<td> <button onclick='borrarElementoClients(" + items[i].id + ")'>Borrar</button></td>";
        myTable += "<td> <button onclick='editarInformacionClients("+ items[i].id + ", \"" + items[i].name + "\", \"" + items[i].email + "\", " + items[i].age+")'>Editar</button></td>";
        myTable += "</tr>";
    }
    myTable += "</table>";
    $("#resultadoClients").append(myTable);
}


function borrarElementoClients(idElemento) {
    let myData={
        id:idElemento
    }
    let dataToSend = JSON.stringify(myData)
    $.ajax({
        url: "https://g8ad3f4f39a8940-dbreto1.adb.ca-toronto-1.oraclecloudapps.com/ords/admin/client/client",
        type: "DELETE",
        data: dataToSend,
        contentType: "application/JSON",
        datatype: "JSON",
        success: function (respuesta) {
            $("#resultadoClients").empty("");
            traerInformacionClients(0);
            console.log("Se ha eliminado");
        }
    });
}

function guardarInformacionClients() {
    $.ajax({
        url: "https://g8ad3f4f39a8940-dbreto1.adb.ca-toronto-1.oraclecloudapps.com/ords/admin/client/client",
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
                    name: $("#nameClients").val(),
                    email: $("#emailClients").val(),
                    age: $("#ageClients").val(),
                }
                $.ajax({
                    url: "https://g8ad3f4f39a8940-dbreto1.adb.ca-toronto-1.oraclecloudapps.com/ords/admin/client/client",
                    type: "POST",
                    data: myData,
                    datatype: "JSON",
                    success: function (respuesta) {
                        $("#resultadoClients").empty("");
                        $("#nameClients").val("");
                        $("#emailClients").val("");
                        $("#ageClients").val("");
                        traerInformacionClients(0);
                        console.log("Se ha guardado!")
                    }
                });
            }, 1000);
        }
    });
    
}

function editarInformacionClients(id, name, email, age) {
    $("#nameClients").val(name);
    $("#emailClients").val(email);
    $("#ageClients").val(age);
    let botonDeActualizar = document.getElementById("actualizarClients");
    botonDeActualizar.removeAttribute("disabled");
    let value = "actualizarInformacionClients("+id+")";
    botonDeActualizar.setAttribute("onclick", value);
}

function actualizarInformacionClients(idElemento) {
    let myData={
        id:idElemento,
        name:$("#nameClients").val(),
        email:$("#emailClients").val(),
        age:$("#ageClients").val(),
    }
    let dataToSend = JSON.stringify(myData)
    $.ajax({
        url: "https://g8ad3f4f39a8940-dbreto1.adb.ca-toronto-1.oraclecloudapps.com/ords/admin/client/client",
        type: "PUT",
        data: dataToSend,
        contentType: "application/JSON",
        datatype: "JSON",
        success: function (respuesta) {
            $("#resultadoClients").empty("");
            $("#nameClients").val("");
            $("#emailClients").val("");
            $("#ageClients").val("");
            traerInformacionClients(0);
            let botonDeActualizar = document.getElementById("actualizarClients");
            botonDeActualizar.removeAttribute("onclick");
            botonDeActualizar.setAttribute("disabled", "");
            console.log("Se ha actualizado!")
        }
    });
}
