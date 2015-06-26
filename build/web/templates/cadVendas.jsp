<br>
<div class="row" ng-hide="!page.editar">
    <div class="col-lg-8">
        <form class="form-horizontal well">
        <fieldset><legend>Vendas</legend><div ng-show="false"  class="control-group">
<label class="control-label" for="page.recEdit.id">Codigo:</label>
<div class="controls">
<input type="text" ng-model="page.recEdit.id" id="page.recEdit.id" class="form-control"ng-disabled="true"  >
</div>
</div>

                            <div class="control-group">
                                <label class="control-label" for="page.recEdit.empresa_id">Empresa:</label>
                                <div class="controls">
                                    <select  
                                        class="form-control"  
                                        ng-model="page.recEdit.empresa_id" 
                                        ng-options="item.nome for item in page.clientes" 
                                        ng-change="chageCliente()" >
                                    </select>
                                </div>
                            </div>

<div  class="control-group">
<label class="control-label" for="page.recEdit.dt_venda">Data Venda:</label>
<div class="controls">
<input type="text" ng-model="page.recEdit.dt_venda" id="page.recEdit.dt_venda" class="form-control"ng-change="changeDataVenda()"  >
</div>
</div>

<div ng-show="false"  class="control-group">
<label class="control-label" for="page.recEdit.valor_unidade">Valor unidade:</label>
<div class="controls">
<input type="text" ng-model="page.recEdit.valor_unidade" id="page.recEdit.valor_unidade" class="form-control"ng-change="chageQtde()"  >
</div>
</div>

<div  class="control-group">
<label class="control-label" for="page.recEdit.quantidade">Quantidade:</label>
<div class="controls">
<input type="text" ng-model="page.recEdit.quantidade" id="page.recEdit.quantidade" class="form-control"ng-change="chageQtde()"  >
</div>
</div>

<div ng-show="false"  class="control-group">
<label class="control-label" for="page.recEdit.taxas">Taxas:</label>
<div class="controls">
<input type="text" ng-model="page.recEdit.taxas" id="page.recEdit.taxas" class="form-control" >
</div>
</div>

<div  class="control-group">
<label class="control-label" for="page.recEdit.total">Total:</label>
<div class="controls">
<input type="text" ng-model="page.recEdit.total" id="page.recEdit.total" class="form-control" >
</div>
</div>

<div  class="control-group">
<label class="control-label" for="page.recEdit.vlr_recebido">Valor recebido:</label>
<div class="controls">
<input type="text" ng-model="page.recEdit.vlr_recebido" id="page.recEdit.vlr_recebido" class="form-control" >
</div>
</div>

<div  class="control-group">
<label class="control-label" for="page.recEdit.observacao">Observações:</label>
<div class="controls">
<input type="text" ng-model="page.recEdit.observacao" id="page.recEdit.observacao" class="form-control" >
</div>
</div>

<div ng-show="false"  class="control-group">
<label class="control-label" for="page.recEdit.contasPagarReceber_id">ID contas a receber:</label>
<div class="controls">
<input type="text" ng-model="page.recEdit.contasPagarReceber_id" id="page.recEdit.contasPagarReceber_id" class="form-control" >
</div>
</div>

         <hr>
          <div class="form-actions">
<button type="submit" ng-click="page.actionSave()" class="btn btn-primary">Gravar</button>

<button type="submit" ng-click="page.actionVoltar()" class="btn btn-primary">Voltar</button>

          </div>
        </fieldset>
</form>
    </div>
</div>

<div class="row" ng-hide="page.editar">
    <div class="col-lg-12">
        <button type="submit" ng-click="page.actionNovo()" class="btn btn-primary">Novo</button>
        <table class="table table-bordered table-striped table-hover">
<thead>
<tr>
<th>Empresa</th>
<th>Data Venda</th>
<th>Quantidade</th>
<th>Taxas</th>
<th>Valor Total</th>
<th>Observações</th>
</tr>
</thead>
<tbody>
<tr ng-repeat="item in page.registro" ng-click="page.actionEditar(item.id)">
<td>{{item.empresa_id.nome}}</td>
<td>{{item.dt_venda}}</td>
<td class="text-right">{{item.quantidade}}</td>
<td class="text-right">{{item.taxas}}</td>
<td class="text-right">{{item.total}}</td>
<td>{{item.observacao}}</td>
</tr>
</tbody>
</table>

        <button type="submit" ng-click="page.actionNovo()" class="btn btn-primary">Novo</button>
    </div>
</div>
