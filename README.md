# ComidaRapida

## Descripción
ComidaRapida es un sistema de gestión para un establecimiento de comida rápida, desarrollado en Java utilizando Swing para su interfaz gráfica.

Permite administrar productos, registrar pedidos, construir combos, aplicar promociones, controlar las ventas y consultar el historial de pedidos.

## Funcionalidades
- Registro y consulta de productos.
- Creación y gestión de pedidos.
- Seguimiento del estado de los pedidos.
- Construcción de combos personalizados.
- Aplicación de promociones y descuentos.
- Registro y control de ventas.
- Historial de pedidos.

## Arquitectura
El proyecto utiliza una organización basada en MVC:
- **Modelo:** representa los datos y las entidades del sistema.
- **Vista:** contiene las ventanas gráficas desarrolladas con Swing.
- **Controlador:** conecta las vistas con la lógica del sistema.
- **Servicios:** administran operaciones y colecciones de datos.

## Patrones de diseño

### Builder
Se utiliza para construir combos personalizados mediante la clase `ComboBuilder`.

### Observer
Se utiliza para notificar los cambios de estado de los pedidos a los observadores de cocina, caja y mesero.

### Strategy
Se utiliza para implementar diferentes estrategias de promociones y descuentos, permitiendo seleccionar una estrategia según el tipo de promoción.

## Tecnologías
- Java.
- Java Swing.
- Programación orientada a objetos.
- Colecciones de Java (`ArrayList`).

## Ejecución

Desde la carpeta raíz del proyecto, ejecutar en PowerShell:

```powershell
javac -d bin (Get-ChildItem -Recurse src/main/java -Filter *.java).FullName
java -cp bin com.comidarapida.Main
```

## Almacenamiento
El sistema utiliza colecciones en memoria para administrar los datos durante la ejecución. No utiliza una base de datos.