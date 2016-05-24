package com.era.licaodecasa.model.adapters;

//public class ListAllTaskAdapter extends BaseAdapter {
//
//	private Context context;
//	private ArrayList<TableTask> tasks; 
//	
//	public ListAllTaskAdapter(Context context, ArrayList<TableTask> tasks) {
//		this.context = context;
//		this.tasks = tasks;
//	}
//	
//	@Override
//	public int getCount() {
//		//Retornando tamanho da lista
//		return tasks.size();
//	}
//
//	@Override
//	public Object getItem(int position) {
//		//Retornando posicao do item na lista.
//		return tasks.get(position);
//	}
//
//	@Override
//	public long getItemId(int position) {
//		// Retornando o id(posicao) do objeto na lista.
//		return position;
//	}
//
//	@Override
//	public View getView(int position, View convertView, ViewGroup parents) {
//		// Criando objeto a partir de posicao no item da lista. 
//		TableTask task = tasks.get(position);
//
//		// Verificando e impedindo de ficar inflando novo layout emtodas as chamadas.
//		View layout;
//		if(convertView == null){
//			// Criando um objeto de LayoutInflater para ser inflado no ListView como item.
//			LayoutInflater inflaterTask = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//			/* 
//			 * Inflando o nosso layout, relativo a uma atividade por vez.
//			 * 1 - Layout que sera o item da lista.
//			 * 2 - Caso queira vincular o layout ao viewGroup, em nosso caso nao queremos(null).
//			 */
//			layout = inflaterTask.inflate(R.layout.single_line_task, null);
//		}else{
//			/* 
//			 * Algumas vezes o layout ja esta inflado e nao necessita mais de ser re-inflado.
//			 * Para economizar processamento deve ser feita a verificacao.
//			 * Caso o convertView(Nosso Layout) seja nulo, sera necessario inflar um novo layout.
//			 * Caso contrareio, a variavel "layout" recebe o mesmo. 
//			 */
//			layout = convertView;
//		}
//		
//		// Iniciando os dados da nossta tarefa no layout, capturando e passando valores para eles.		
//		TextView tvTaskQuestion = (TextView) layout.findViewById(R.id.SingleLineText_TVQuestion);
//		//tvTaskQuestion.setText(task.getDiscipline());
//		
//		// Retornando a linha do nosso layot personalizado.
//		return layout;
//	}
//
//}
