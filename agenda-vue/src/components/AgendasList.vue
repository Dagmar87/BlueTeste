<template>
    <div class="list row">
        <div class="col-md-8">
            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="Pesquisar por nome" v-model="nome" />
                <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="button" @click="searchNome">
                        Pesquisar
                    </button>
                </div>
            </div>
        </div>
        <div class="col-md-6">
            <h4>Lista de Contatos</h4>
            <ul class="list-group">
                <li class="list-group-item" :class="{ active: index == currentIndex }"
                    v-for="(agenda, index) in agendas" :key="index" @click="setActiveAgenda(agenda, index)">
                    {{ agenda.nome }}
                </li>
            </ul>
            <button class="m-3 btn btn-sm btn-danger" @click="removeAllAgendas">
                Excluir tudo
            </button>
        </div>
        <div class="col-md-6">
            <div v-if="currentAgenda">
                <h4>Contato</h4>
                <div>
                    <label><strong>Nome:</strong></label> {{ currentAgenda.nome }}
                </div>
                <div>
                    <label><strong>Email:</strong></label> {{ currentAgenda.email }}
                </div>
                <div>
                    <label><strong>Telefone:</strong></label> {{ currentAgenda.telefone }}
                </div>
                <a class="badge badge-warning" :href="'/agendas/' + currentAgenda.id">
                    Editar
                </a>
            </div>
            <div v-else>
                <br />
                <p>Por favor, clique em um contato...</p>
            </div>
        </div>
    </div>
</template>
<script>
import AgendaDataService from "../services/AgendaDataService";

export default {
    name: "agendas-list",
    data() {
        return {
            agendas: [],
            currentAgenda: null,
            currentIndex: -1,
            nome: ""
        };
    },
    methods: {
        retrieveAgendas() {
            AgendaDataService.getAll()
                .then(response => {
                    this.agendas = response.data;
                    console.log(response.data);
                })
                .catch(e => {
                    console.log(e);
                });
        },
        refreshList() {
            this.retrieveAgendas();
            this.currentAgenda = null;
            this.currentIndex = -1;
        },
        setActiveAgenda(agenda, index) {
            this.currentAgenda = agenda;
            this.currentIndex = index;
        },
        removeAllAgendas() {
            AgendaDataService.deleteAll()
                .then(response => {
                    console.log(response.data);
                    this.refreshList();
                })
                .catch(e => {
                    console.log(e);
                });
        },
        searchNome() {
            AgendaDataService.findByNome(this.nome)
                .then(response => {
                    this.agendas = response.data;
                    console.log(response.data);
                })
                .catch(e => {
                    console.log(e);
                });
        }
    },
    mounted() {
        this.retrieveAgendas();
    }
};
</script>
<style>
.list {
    text-align: left;
    max-width: 750px;
    margin: auto;
}
</style>