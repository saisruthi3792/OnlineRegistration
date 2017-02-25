<br>
<br>
<br>
<%-- Section to display description --%>
<section class="copyright">
        <c:if test="${cookie.containsKey('host')}">
        Cookie info - Host: ${cookie.host.value}, Port: ${cookie.port.value}<br>
    </c:if> 
    &copy; Researchers Exchange Participations &nbsp;
  
</section>
</body>
</html>

