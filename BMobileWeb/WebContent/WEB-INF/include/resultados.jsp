<%-- Solo se muestra si hay resultados de una operaci&oacute;n que mostrar --%>
<% if (request.getAttribute("resultados") != null) {%>
<style>
.notify{
	text-align: center;
	font: normal;
    background-color: #333;
    color: #FFF;
    width: 1024px;
    height: auto;
    margin-top:0px;
    margin-left:50%;
	left:-512px;
    -moz-border-radius:10px;
    -webkit-border-radius:10px;
    border-radius:10px;
}
.notify p{
    padding: 10px;
    margin: 0;
}
.notify.error{
    background-color: #F00;
}
</style>
<script type="text/javascript">
<!--
//Función que crea las notificaciones
function notify(msg,speed,fadeSpeed,type){

    //Borra cualquier mensaje existente
    $('.notify').remove();

    //Si el temporizador para hacer desaparecer el mensaje está
    //activo, lo desactivamos.
    if (typeof fade != "undefined"){
        clearTimeout(fade);
    }

    //Creamos la notificación con la clase (type) y el texto (msg)
    $('body').append('<div class="notify '+type+'" style="display:none;position:fixed;"><p>'+msg+'</p></div>');

    //Calculamos la altura de la notificación.
    notifyHeight = $('.notify').outerHeight();

    //Creamos la animación en la notificación con la velocidad
    //que pasamos por el parametro speed
    $('.notify').css('top',-notifyHeight).animate({top:10,opacity:'toggle'},speed);

    //Creamos el temporizador para hacer desaparecer la notificación
    //con el tiempo almacenado en el parametro fadeSpeed
    fade = setTimeout(function(){

        $('.notify').animate({top:notifyHeight+10,opacity:'toggle'}, speed);

    }, fadeSpeed);

}
//-->
</script>
	<%String mensaje = (String)request.getAttribute("resultados");%>
<script type="text/javascript">
<!--
	notify('<%= mensaje%>',500,30000);
//-->
</script>	
<% }%>
 