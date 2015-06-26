hidroLiveApp.service('serviceCadasrto', function ($http) {

    var page = {};
    this.setData = function (rec) {
        page.registro = rec;
    };
    this.getPage = function () {
        page = {
            urlGravar: "",
            editar: false,
            registro: {},
            uid: 1,
            recDefault: {id: 0},
            actionNovo: function () {
                page.recEdit = angular.copy(page.recDefault);
                page.editar = true;
            },
            actionEditar: function (id) {
                page.recEdit = angular.copy(this.actionGet(id));
                page.ajustarReferenciasRegistro(page.recEdit);
                page.editar = true;
            },
            actionGet: function (id) {
                for (var i in page.registro) {
                    if (page.registro[i].id === id) {
                        return page.registro[i];
                    }
                }
            },
            actionSave: function () {
                page.saveDB(page.urlGravar);
                if (page.recEdit.id === "0" || page.recEdit.id === 0) {
                    page.recEdit.id = page.uid++;
                    page.recEdit.id = 0;
                    page.registro.push(page.recEdit);
                } else {
                    for (var i in page.registro) {
                        if (page.registro[i].id === page.recEdit.id) {
                            page.registro[i] = page.recEdit;
                        }
                    }
                }
                page.recEdit = {};
                this.editar = false;
            },
            actionVoltar: function () {
                page.recEdit = {};
                this.editar = false;
            },
            actionDelete: function (id) {

                for (var i in page.registro) {
                    if (page.registro[i].id === id) {
                        page.registro.splice(i, 1);
                    }
                }
                if (page.recEdit.id === id) {
                    page.recEdit = {};
                }
            },
            saveDB: function (url) {
                var idAntes = page.recEdit.id;
                $http.post(url, page.recEdit).success(function (data, status) {
                    page.resultado = data;
                    page.ajustarReferenciasRegistro(data.rec);
                    for (var i in page.registro) {
                        if (page.registro[i].id === idAntes) {
                            page.registro[i] = data.rec;
                        }
                    }
                });
            },
            retrieve: function (url) {
                $http.post(url).success(function (data, status) {
                    page.registro = data;
                });
            },
            tirarFormatacaoNumerica: function (str) {
                str = str.replace('.', '');
                ;
                str = str.replace(',', '.');
                ;
                return str;
            },
            poeFormatacaoNumerica: function (str) {
                str = str + '';
                str = str.replace('.', ',');
                ;
                return str;
            },
            currentDate: function () {
                var today = new Date();
                var dia = '0' + today.getDate();
                var mes = '0' + (today.getMonth() + 1);
                var today1 = dia.substr(dia.length - 2)
                        + "/"
                        + mes.substr(mes.length - 2)
                        + "/"
                        + today.getFullYear();
                return today1;
            },
            setRefTipoMovimento: function (rec) {
                if (rec.tipo_movimento_id > 0 || rec.tipo_movimento_id.id > 0) {
                    for (var j in page.tipo_movimento) {
                        if (page.tipo_movimento[j].id === rec.tipo_movimento_id || page.tipo_movimento[j].id === rec.tipo_movimento_id.id) {
                            rec.tipo_movimento_id = page.tipo_movimento[j];
                            break;
                        }
                    }
                }
            },
            setRefCidades: function (rec) {
                if (rec.cidade_id > 0 || rec.cidade_id.id > 0) {
                    for (var j in page.cidades) {
                        if (page.cidades[j].id === rec.cidade_id || page.cidades[j].id === rec.cidade_id.id) {
                            rec.cidade_id = page.cidades[j];
                            break;
                        }
                    }
                }
            },
            setRefClientes: function (rec) {
                if (rec.empresa_id > 0 || rec.empresa_id.id > 0) {
                    for (var j in page.clientes) {
                        if (page.clientes[j].id === rec.empresa_id || page.clientes[j].id === rec.empresa_id.id) {
                            rec.empresa_id = page.clientes[j];
                            break;
                        }
                    }
                }
            },
            setRefContasDebitoCredito: function (rec) {
                if (rec.conta_debito_id > 0 || rec.conta_debito_id.id > 0) {
                    for (var j in page.contas) {
                        if (page.contas[j].id === rec.conta_debito_id || page.contas[j].id === rec.conta_debito_id.id) {
                            rec.conta_debito_id = page.contas[j];
                            break;
                        }
                    }
                }
                if (rec.conta_credito_id > 0 || rec.conta_credito_id.id > 0) {
                    for (var j in page.contas) {
                        if (page.contas[j].id === rec.conta_credito_id || page.contas[j].id === rec.conta_credito_id.id) {
                            rec.conta_credito_id = page.contas[j];
                            break;
                        }
                    }
                }
            },
            setRefContas: function (rec) {
                if (rec.conta_id > 0 || rec.conta_id.id > 0) {
                    for (var j in page.contas) {
                        if (page.contas[j].id === rec.conta_id || page.contas[j].id === rec.conta_id.id) {
                            rec.conta_id = page.contas[j];
                            break;
                        }
                    }
                }
            },
            ajustarReferenciasRegistro: function (rec) {
            },
            ajustarReferenciasTodas: function () {
                for (var i in page.registro) {
                    page.ajustarReferenciasRegistro(page.registro[i]);
                }
            },
            retrieveCidades: function () {
                $http.post('./json/cidades.jsp').success(function (data, status) {
                    page.registro = data;
                });
            },
            retrieveTipoMovimento: function () {
                return [
                    {id: '1', "nome": "Conta a receber"},
                    {id: '2', "nome": "Conta a pagar"},
                    {id: '3', "nome": "Conta recebida"},
                    {id: '4', "nome": "Conta paga"}
                ];
            },
            retrieveClientes: function () {
                $http.post('./json/clientes.jsp').success(function (data, status) {
                    page.cidades = data.cidades;
                    page.registro = data.clientes;
                    page.ajustarReferenciasTodas();
                });
            },
            retrieveContasRP: function () {
                $http.post('./json/ContasReceberPagar.jsp').success(function (data, status) {
                    page.clientes = data.clientes;
                    page.contas = data.contas;
                    page.registro = data.contasReceberPagar;
                    page.tipo_movimento = page.retrieveTipoMovimento();
                    page.ajustarReferenciasTodas();
                });
            },
            retrieveVendas: function () {
                $http.post('./json/vendas.jsp').success(function (data, status) {
                    page.clientes = data.clientes;
                    page.registro = data.vendas;
                    page.ajustarReferenciasTodas();
                });
            },
            retrieveMovContas: function () {
                $http.post('./json/movContas.jsp').success(function (data, status) {
                    if (page.contas === undefined) {
                        page.contas = data.contas;
                    }
                    page.registro = data.movContas;
                    page.ajustarReferenciasTodas();
                });
            },
            retrieveExtratoRP: function (dados) {
                $http.post('./json/relContasRP.jsp', dados).success(function (data, status) {
                    if (page.clientes === undefined) {
                        page.clientes = data.clientes;
                    }
                    page.registro = data.relatorio;
                });
            }
        };
        return page;
    }
    ;
});