package com.crud.tasks.domain;

import lombok.Data;

@Data
public class Badges {

    private int votes;
    private AttachmentsByType attachments;

    @Data
    public class AttachmentsByType {

        private Trello trello;
        @Data
        public class Trello {

            private int board;
            private int card;
        }
    }
}