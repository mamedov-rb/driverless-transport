create table public.transport
(
    id            uuid         not null
        primary key,
    model         varchar(255) not null,
    serial_number varchar(255) not null
        constraint serial_number_constraint
            unique,
    status        varchar(255) not null,
    type          varchar(255) not null,
    created_at    timestamp(6) not null,
    updated_at    timestamp(6) not null,
    created_by    varchar(255) not null,
    updated_by    varchar(255) not null
);

alter table public.transport
    owner to postgres;

create index idx_serial_number on transport (serial_number);
