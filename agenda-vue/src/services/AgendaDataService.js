import http from "../http-common";

class AgendaDataService {
  getAll() {
    return http.get("/agendas");
  }
  get(id) {
    return http.get(`/agendas/${id}`);
  }
  create(data) {
    return http.post("/agendas", data);
  }
  update(id, data) {
    return http.put(`/agendas/${id}`, data);
  }
  delete(id) {
    return http.delete(`/agendas/${id}`);
  }
  deleteAll() {
    return http.delete(`/agendas`);
  }
  findByNome(nome) {
    return http.get(`/agendas?nome=${nome}`);
  }
}

export default new AgendaDataService();
