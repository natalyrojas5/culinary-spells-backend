# 📝 DESCRIPCIÓN DE CAMBIOS 

<!-- OBLIGATORIO -->
<!-- Por favor incluya un resumen de los cambios realizados. -->

- Texto de descripción

## 📚 TIPO DE CAMBIO 

<!-- OBLIGATORIO -->
<!-- Por favor incluya un resumen de los cambios realizados. -->

- [ ] FEATURE - Nueva característica para el usuario
- [ ] FIX - Arregla un bug que afecta al usuario
- [ ] BUILD - Cambios en el sistema de build, tareas de despliegue o instalación
- [ ] DOCS - Cambios en la documentación
- [ ] REFACTOR - Refactorización del código como cambios de nombre de variables o funciones.
- [ ] TEST - Añade tests o refactoriza uno existente.

## 🔗 ENLACES

<!-- OPCIONAL -->
<!-- Por favor incluya enlace de Figma, Jira, etc en caso de tenerlos. -->

- [Figma](https://..)
- [Ticket Trello](https://..)

## 🛎 IMPORTANTE

A la hora de integrar los cambios utilizar **Squash and merge** o **Merge commit** según corresponda:

| Branch desde |   Branch hacia    | Merge commit | Squash and Merge |
| :----------: | :---------------: | :----------: | :--------------: |
|   feature/   |      develop      |      X       |        ✓         |
|   hotfix/    | master - release/ |      X       |        ✓         |
|     fix/     |     release/      |      X       |        ✓         |
|   release/   |      master       |      ✓       |        X         |
|  backport/   |      develop      |      ✓       |        X         |

