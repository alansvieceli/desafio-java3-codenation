package main.java.challenge;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Main {

	private class Jogador {

		private Integer id; // 0
		private String name; // 1
		private String full_name; // 2
		private String club; // 3
		private Integer age; // 6
		private String nationality; // 14
		private Double eur_release_clause; // 18
		private Date birth_date; // 8
		private Double eur_wage; // 17

		public Jogador(Integer id, String name, String full_name, String club, Integer age, String nationality,
				Double eur_release_clause, Date birth_date, Double eur_wage) {
			this.id = id;
			this.name = name;
			this.full_name = full_name;
			this.club = club;
			this.age = age;
			this.nationality = nationality;
			this.eur_release_clause = eur_release_clause;
			this.birth_date = birth_date;
			this.eur_wage = eur_wage;
		}

		public String getFull_name() {
			return full_name;
		}

		public String getClub() {
			return club;
		}

		public Integer getAge() {
			return age;
		}

		public String getNationality() {
			return nationality;
		}

		public Double getEur_release_clause() {
			return eur_release_clause;
		}

		public Date getBirth_date() {
			return birth_date;
		}

		public Double getEur_wage() {
			return eur_wage;
		}
	}


	private List<Jogador> jogadores = new ArrayList<>();

	public Main() {		
		carregarJogadores("data.csv");
	}

	public void carregarJogadores(String arq) {

		ClassLoader classLoader = getClass().getClassLoader();
		try (BufferedReader br = new BufferedReader(new FileReader(classLoader.getResource(arq).getFile()))) {
			br.readLine(); // itnora titulo
			String linha = br.readLine();
			while (linha != null) {
				String[] fields = linha.split(",");
				linha = br.readLine();

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date birth_date = null;
				try {
					birth_date = sdf.parse(fields[8]);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
				
				if (fields[18].equals("")) {
					fields[18] = "0";
				}
				
				jogadores.add(new Jogador(Integer.parseInt(fields[0]), fields[1], fields[2], fields[3],
						Integer.parseInt(fields[6]), fields[14], Double.parseDouble(fields[18]), birth_date,
						Double.parseDouble(fields[17])));
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Quantas nacionalidades (coluna `nationality`) diferentes existem no arquivo?
	public int q1() {
		return jogadores.stream().collect(Collectors.groupingBy(Jogador::getNationality)).size();
	}

	// Quantos clubes (coluna `club`) diferentes existem no arquivo?
	// Obs: Existem jogadores sem clube.
	public int q2() {		
		return jogadores.stream().filter(j -> !j.getClub().equals("")).collect(Collectors.groupingBy(Jogador::getClub)).size();
	}

	// Liste o primeiro nome (coluna `full_name`) dos 20 primeiros jogadores.
	public List<String> q3() {
		//return jogadores.stream().limit(20).map(j -> j.getFull_name().substring(0, j.getFull_name().indexOf(" "))).collect(Collectors.toList());
		return jogadores.stream().limit(20).map(j -> j.getFull_name()).collect(Collectors.toList());
	}

	// Quem são os top 10 jogadores que possuem as maiores cláusulas de rescisão?
	// (utilize as colunas `full_name` e `eur_release_clause`)
	public List<String> q4() {
		Comparator<Jogador> comp = (j1, j2) -> j1.getEur_release_clause().compareTo(j2.getEur_release_clause());
		//return jogadores.stream().sorted(comp.reversed()).limit(10).map(j -> j.getFull_name().substring(0, j.getFull_name().indexOf(" "))).collect(Collectors.toList());
		return jogadores.stream().sorted(comp.reversed()).limit(10).map(j -> j.getFull_name()).collect(Collectors.toList());
	}

	// Quem são os 10 jogadores mais velhos (use como critério de desempate o campo `eur_wage`)?
	// (utilize as colunas `full_name` e `birth_date`)
	public List<String> q5() {
		Comparator<Jogador> comp = Comparator.comparing(Jogador::getBirth_date).thenComparing(Jogador::getEur_wage);
		return jogadores.stream().sorted(comp).limit(10).map(j -> j.getFull_name()).collect(Collectors.toList());
	}

	// Conte quantos jogadores existem por idade. Para isso, construa um mapa onde as chaves são as idades e os valores a contagem.
	// (utilize a coluna `age`)
	public Map<Integer, Integer> q6() {
		return jogadores.stream().collect(Collectors.groupingBy(Jogador::getAge, Collectors.summingInt(x -> 1)));
	}

}
