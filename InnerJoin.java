import java.util.*;

public class InnerJoin
{
	public static void main(String[] args)
	{
		String[] columns1 = {"Name", "ID"};
		Table table1 = new Table("Student", columns1);

		String[] columns2 = {"Course", "ID", "SID"};
		Table table2 = new Table("Enrollment", columns2);

		String[][] rows1 = {
			{"Josh","101"},
			{"Bob", "102"},
			{"Mark", "103"},
			{"Steve", "104"}
		};

		table1.insert(rows1);

		String[][] rows2 = {
			{"Database Implementation", "4340", "101"},
			{"Database Implementation", "4340", "102"},
			{"Database Implementation", "4340", "103"},
			{"Database Implementation", "4340", "104"},
			{"Computer Security", "4540", "103"},
			{"Computer Security", "4540", "101"},
			{"Graph Theory", "4060", "102"},
			{"Graph Theory", "4060", "101"}
		};

		table2.insert(rows2);

		System.out.println(table1.print());
		System.out.println(table2.print());

		System.out.println(Table.join(table1, "ID",table2, "SID").print());
	}
}	
class Table {
		public final List<String[]> data;
		public final String[] columns;
		public final Map<String, Integer> columnNameToIndex;
		public final String name;

		public Table(String name, String[] columns) {
			this.name = name;
			this.data = new ArrayList<>();
			this.columns = columns;
			this.columnNameToIndex = new HashMap<String,Integer>();
			for(int i = 0; i < columns.length; i++) {
				this.columnNameToIndex.put(columns[i], i);
			}
		}

		public void insert(String[]... rows) {
			for (String[] row : rows) {
				this.data.add(row);
			}
		}
		public void insertBulk(String[][] rows) {
			for (String[] row : rows) {
				this.data.add(row);
			}
		}

		public String print() {
			String result = "TABLE " + this.name + "\n";

			for (String column : this.columns) {
				result = result + column + ",";
			}

			result = result + "\n";

			for (String[] row : this.data) {
				for (String value : row) {
					result = result + value + ",";
				}
				result = result + "\n";
			}

			return result;
		}

		public static Table join(Table r, String rColumn, Table s, String sColumn) {
	        String[] columns = new String[r.columns.length + s.columns.length];
	        System.arraycopy(r.columns, 0, columns, 0, r.columns.length);
	        System.arraycopy(s.columns, 0, columns, r.columns.length, s.columns.length);

	        // prefix the new columns with the previous tablenames
	        for(int i = 0; i < r.columns.length; i++) {
	        	columns[i] = r.name + "." + columns[i];
	        }
	        for(int i = r.columns.length; i < columns.length; i++) {
	        	columns[i] = s.name + "." + columns[i];
	        }

			Table joined = new Table(r.name + "-JOIN-" + s.name, columns);

			Integer rIndex = r.columnNameToIndex.get(rColumn);
			Integer sIndex = s.columnNameToIndex.get(sColumn);

			for(String[] rRow: r.data) {
				for (String[] sRow: s.data) {
					if(rRow[rIndex].equals(sRow[sIndex])) {
				        String[] row = new String[rRow.length + sRow.length];
				        System.arraycopy(rRow, 0, row, 0, rRow.length);
				        System.arraycopy(sRow, 0, row, rRow.length, sRow.length);
						joined.insert(row);
					}
				}
			}

			return joined;
		}
	}
