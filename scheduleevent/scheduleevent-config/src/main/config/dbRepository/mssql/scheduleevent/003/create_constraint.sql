alter table sc_scheduleevent_list
        add constraint pk_scheduleevent_list
        primary key (id);
alter table sc_scheduleevent_options
        add constraint pk_scheduleevent_options
        primary key (id);
alter table sc_scheduleevent_contributor
        add constraint pk_scheduleevent_contrib
        primary key (id);
alter table sc_scheduleevent_response
        add constraint pk_scheduleevent_response
        primary key (id);
                
alter table sc_scheduleevent_options
        add constraint fk_options_eventid
         foreign key (scheduleeventid)
        references sc_scheduleevent_list (id);
alter table sc_scheduleevent_contributor
        add constraint fk_contributor_scheduleeventid
         foreign key (scheduleeventid)
        references sc_scheduleevent_list (id); 
alter table sc_scheduleevent_response
        add constraint fk_response_scheduleeventid
         foreign key (scheduleeventid)
        references sc_scheduleevent_list (id);        
alter table sc_scheduleevent_response
        add constraint fk_response_optionid
        foreign key (optionid)
        references sc_scheduleevent_options (id);                
                