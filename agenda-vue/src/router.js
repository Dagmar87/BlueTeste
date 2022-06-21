import Vue from "vue";
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
  mode: "history",
  routes: [
    {
      path: "/",
      alias: "/agendas",
      name: "agendas",
      component: () => import("./components/AgendasList"),
    },
    {
      path: "/agendas/:id",
      name: "agenda-details",
      component: () => import("./components/Agenda"),
    },
    {
      path: "/add",
      name: "add",
      component: () => import("./components/AddAgenda"),
    },
  ],
});
