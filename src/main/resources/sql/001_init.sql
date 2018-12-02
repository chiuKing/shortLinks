BEGIN TRANSACTION;
    DROP TABLE IF EXISTS "links" CASCADE;
    DROP SEQUENCE IF EXISTS "links_seq" CASCADE;

    CREATE SEQUENCE "links_seq" START 1000000;

    CREATE TABLE "links"(
        "id" BIGINT PRIMARY_KEY DEFAULT "nextval"('"linksq_seq"'),
        "text" TEXT NOT NULL
    );

END TRANSACTION