<br>
<div class="row" ng-hide="!page.editar">
    <div class="col-lg-8">
        <form class="form-horizontal well">
        <fieldset><legend>Movimento de Contas</legend><div ng-show="false"  class="control-group">
<label class="control-label" for="page.recEdit.id">Codigo:</label>
<div class="controls">
<input type="text" ng-model="page.recEdit.id" id="page.recEdit.id" class="form-control"ng-disabled="true"  >
</div>
</div>

                            <div class="control-group">
                                <label class="control-label" for="page.recEdit.conta_credito_id">Conta crédito:</label>
                                <div class="controls">
                                    <select  
                                        class="form-control"  
                                        ng-model="page.recEdit.conta_credito_id" 
                                        ng-options="item.nome for item in page.contas" 
                                        >
                                    </select>
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="page.recEdit.conta_debito_id">Conta débito:</label>
                                <div class="controls">
                                    <select  
                                        class="form-control"  
                                        ng-model="page.recEdit.conta_debito_id" 
                                        ng-options="item.nome for item in page.contas" 
                                        >
                                    </select>
                                </div>
                            </div>

<div  class="control-group">
<label class="control-label" for="page.recEdit.dt_movto">Data:</label>
<div class="controls">
<input type="text" ng-model="page.recEdit.dt_movto" id="page.recEdit.dt_movto" class="form-control" >
</div>
</div>

<div  class="control-group">
<label class="control-label" for="page.recEdit.valor">Valor:</label>
<div class="controls">
<input type="text" ng-model="page.recEdit.valor" id="page.recEdit.valor" class="form-control" >
</div>
</div>

<div  class="control-group">
<label class="control-label" for="page.recEdit.observacao">Observações:</label>
<div class="controls">
<input type="text" ng-model="page.recEdit.observacao" id="page.recEdit.observacao" class="form-control" >
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
<th>Código</th>
<th>Conta debito</th>
<th>Conta crédito</th>
<th>Data Venda</th>
<th>Valor</th>
<th>Observações</th>
</tr>
</thead>
<tbody>
<tr ng-repeat="item in page.registro" ng-click="page.actionEditar(item.id)">
<td>{{item.id}}</td>
<td>{{item.conta_debito_id.nome}}</td>
<td>{{item.conta_credito_id.nome}}</td>
<td>{{item.dt_movto}}</td>
<td class="text-right">{{item.valor}}</td>
<td>{{item.observacao}}</td>
</tr>
</tbody>
</table>

        <button type="submit" ng-click="page.actionNovo()" class="btn btn-primary">Novo</button>
    </div>
</div>
