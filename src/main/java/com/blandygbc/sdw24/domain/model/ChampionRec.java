package com.blandygbc.sdw24.domain.model;

public record ChampionRec(
                Long id,
                String nome,
                String role,
                String lore,
                String imageUrl) {

        public String generateContextByQuestion(String question) {
                return """
                                Pergunta: %s
                                Nome do Campeão: %s
                                Função: %s
                                Lore (História): %s
                                        """.formatted(question, this.nome, this.role, this.lore);
        }

}
