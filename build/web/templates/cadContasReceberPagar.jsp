<br>
<div class="row" ng-hide="!page.editar">
    <div class="col-lg-8">
        <form class="form-horizontal well">
        <fieldset><legend>Clientes</legend><div  class="control-group">
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
                                        >
                                    </select>
                                </div>
                            </div>

<div  class="control-group">
<label class="control-label" for="page.recEdit.dt_emissao">Emissão:</label>
<div class="controls">
<input type="text" ng-model="page.recEdit.dt_emissao" id="page.recEdit.dt_emissao" class="form-control" >
</div>
</div>

                            <div class="control-group">
                                <label class="control-label" for="page.recEdit.tipo_movimento_id">Tipo movimento:</label>
                                <div class="controls">
                                    <select  
                                        class="form-control"  
                                        ng-model="page.recEdit.tipo_movimento_id" 
                                        ng-options="item.nome for item in page.tipo_movimento" 
                                        >
                                    </select>
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label" for="page.recEdit.conta_id">Conta:</label>
                                <div class="controls">
                                    <select  
                                        class="form-control"  
                                        ng-model="page.recEdit.conta_id" 
                                        ng-options="item.nome for item in page.contas" 
                                        >
                                    </select>
                                </div>
                            </div>

<div  class="control-group">
<label class="control-label" for="page.recEdit.valor_desconto">Desconto:</label>
<div class="controls">
<input type="text" ng-model="page.recEdit.valor_desconto" id="page.recEdit.valor_desconto" class="form-control" >
</div>
</div>

<div  class="control-group">
<label class="control-label" for="page.recEdit.valor_acrescimo">Acrescimo:</label>
<div class="controls">
<input type="text" ng-model="page.recEdit.valor_acrescimo" id="page.recEdit.valor_acrescimo" class="form-control" >
</div>
</div>

<div  class="control-group">
<label class="control-label" for="page.recEdit.valor_total">Total:</label>
<div class="controls">
<input type="text" ng-model="page.recEdit.valor_total" id="page.recEdit.valor_total" class="form-control" >
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
<th>Empresa</th>
<th>Emissão</th>
<th>Tipo movimento</th>
<th>Conta</th>
<th>Valor Total</th>
<th>Observações</th>
</tr>
</thead>
<tbody>
<tr ng-repeat="item in page.registro" ng-click="page.actionEditar(item.id)">
<td>{{item.id}}</td>
<td>{{item.empresa_id.nome}}</td>
<td>{{item.dt_emissao}}</td>
<td>{{item.tipo_movimento_id.nome}}</td>
<td>{{item.conta_id.nome}}</td>
<td class="text-right">{{item.valor_total}}</td>
<td>{{item.observacao}}</td>
</tr>
</tbody>
</table>

        <button type="submit" ng-click="page.actionNovo()" class="btn btn-primary">Novo</button>
    </div>
</div>
