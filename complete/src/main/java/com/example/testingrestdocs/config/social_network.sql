--
-- PostgreSQL database dump
--

-- Dumped from database version 12.1
-- Dumped by pg_dump version 12.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category (
    id integer NOT NULL,
    name character varying(100),
    description character varying(500),
    "characteristicId" integer
);


ALTER TABLE public.category OWNER TO postgres;

--
-- Name: category_x_charasterictic; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category_x_charasterictic (
    "categoryId" integer,
    "characteristicId" integer
);


ALTER TABLE public.category_x_charasterictic OWNER TO postgres;

--
-- Name: characteristic; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.characteristic (
    id integer NOT NULL,
    name character varying(100),
    type character varying(100),
    refillable character varying(100)
);


ALTER TABLE public.characteristic OWNER TO postgres;

--
-- Name: comment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.comment (
    id integer NOT NULL,
    text character varying(1000),
    date date
);


ALTER TABLE public.comment OWNER TO postgres;

--
-- Name: post; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.post (
    id integer NOT NULL,
    text character varying(1000) NOT NULL,
    date date,
    material character varying(1000),
    isblocked boolean,
    "userId" integer NOT NULL,
    "categoryId" integer,
    "commentId" integer,
    "characteristicId" integer
);


ALTER TABLE public.post OWNER TO postgres;

--
-- Name: post_x_category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.post_x_category (
    "postId" integer NOT NULL,
    "categoryId" integer NOT NULL
);


ALTER TABLE public.post_x_category OWNER TO postgres;

--
-- Name: post_x_characterictic; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.post_x_characterictic (
    "postId" integer,
    "characteristicId" integer
);


ALTER TABLE public.post_x_characterictic OWNER TO postgres;

--
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."user" (
    id integer NOT NULL,
    username character varying(100),
    "clientToken" character varying(50),
    email character varying,
    phone character varying(20)
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- Name: user_x_category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_x_category (
    "userId" integer,
    "categoryId" integer
);


ALTER TABLE public.user_x_category OWNER TO postgres;

--
-- Name: user_x_subscription; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_x_subscription (
    "userId" integer,
    "subscribId" integer
);


ALTER TABLE public.user_x_subscription OWNER TO postgres;

--
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.category (id, name, description, "characteristicId") FROM stdin;
1	fun	it is a category about different funny things	1
2	animals	it is a category about cuttie animals	1
3	cars	this category about different cars	2
\.


--
-- Data for Name: category_x_charasterictic; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.category_x_charasterictic ("categoryId", "characteristicId") FROM stdin;
1	1
\.


--
-- Data for Name: characteristic; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.characteristic (id, name, type, refillable) FROM stdin;
1	super	1	\N
2	cool	1	\N
\.


--
-- Data for Name: comment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.comment (id, text, date) FROM stdin;
1	it is really funny	2019-12-12
2	it 's so cute	2020-09-12
3	not really interesting(	2018-09-09
\.


--
-- Data for Name: post; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.post (id, text, date, material, isblocked, "userId", "categoryId", "commentId", "characteristicId") FROM stdin;
1	Покупает мужик шляпу - а она ему как раз	2019-01-01	\N	f	1	1	1	1
2	Лемуры — примитивные приматы, латинское название которых в переводе означает дух, привидение	2020-03-04	\N	f	2	2	2	2
3	Российским водителям напомнили правила прогрева автомобиля	2017-08-09	\N	f	2	2	3	2
\.


--
-- Data for Name: post_x_category; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.post_x_category ("postId", "categoryId") FROM stdin;
1	2
\.


--
-- Data for Name: post_x_characterictic; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.post_x_characterictic ("postId", "characteristicId") FROM stdin;
1	1
\.


--
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."user" (id, username, "clientToken", email, phone) FROM stdin;
1	Michael	REGISTERED	michael@gmail.com	89153107778
2	Jack	REGISTERED	jack@mail.ru	89173779955
3	Pavel	REGISTERED	pavlik@mail.ru	89154343433
4	Spiderman	REGISTERED	spider@man.ru	89176262626
\.


--
-- Data for Name: user_x_category; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_x_category ("userId", "categoryId") FROM stdin;
1	2
\.


--
-- Data for Name: user_x_subscription; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.user_x_subscription ("userId", "subscribId") FROM stdin;
1	2
1	3
\.


--
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- Name: characteristic characteristic_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.characteristic
    ADD CONSTRAINT characteristic_pkey PRIMARY KEY (id);


--
-- Name: comment comment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment
    ADD CONSTRAINT comment_pkey PRIMARY KEY (id);


--
-- Name: post post_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.post
    ADD CONSTRAINT post_pkey PRIMARY KEY (id);


--
-- Name: user user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id);


--
-- Name: post categoryId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.post
    ADD CONSTRAINT "categoryId" FOREIGN KEY ("categoryId") REFERENCES public.category(id) NOT VALID;


--
-- Name: user_x_category categoryId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_x_category
    ADD CONSTRAINT "categoryId" FOREIGN KEY ("categoryId") REFERENCES public.category(id) NOT VALID;


--
-- Name: post_x_category categoryId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.post_x_category
    ADD CONSTRAINT "categoryId" FOREIGN KEY ("categoryId") REFERENCES public.category(id);


--
-- Name: category_x_charasterictic category_x_charasterictic_categoryId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category_x_charasterictic
    ADD CONSTRAINT "category_x_charasterictic_categoryId_fkey" FOREIGN KEY ("categoryId") REFERENCES public.category(id);


--
-- Name: category_x_charasterictic category_x_charasterictic_characteristicId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category_x_charasterictic
    ADD CONSTRAINT "category_x_charasterictic_characteristicId_fkey" FOREIGN KEY ("characteristicId") REFERENCES public.characteristic(id);


--
-- Name: post_x_characterictic charactericticId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.post_x_characterictic
    ADD CONSTRAINT "charactericticId" FOREIGN KEY ("characteristicId") REFERENCES public.characteristic(id);


--
-- Name: post characteristicId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.post
    ADD CONSTRAINT "characteristicId" FOREIGN KEY ("characteristicId") REFERENCES public.characteristic(id) NOT VALID;


--
-- Name: category characteristicId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT "characteristicId" FOREIGN KEY ("characteristicId") REFERENCES public.characteristic(id) NOT VALID;


--
-- Name: post commentId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.post
    ADD CONSTRAINT "commentId" FOREIGN KEY ("commentId") REFERENCES public.comment(id) NOT VALID;


--
-- Name: post_x_category postId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.post_x_category
    ADD CONSTRAINT "postId" FOREIGN KEY ("postId") REFERENCES public.post(id);


--
-- Name: post_x_characterictic postId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.post_x_characterictic
    ADD CONSTRAINT "postId" FOREIGN KEY ("postId") REFERENCES public.post(id);


--
-- Name: post userId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.post
    ADD CONSTRAINT "userId" FOREIGN KEY ("userId") REFERENCES public."user"(id) NOT VALID;


--
-- Name: user_x_category userId; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_x_category
    ADD CONSTRAINT "userId" FOREIGN KEY ("userId") REFERENCES public."user"(id) NOT VALID;


--
-- Name: user_x_subscription user_x_subscription_subscribId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_x_subscription
    ADD CONSTRAINT "user_x_subscription_subscribId_fkey" FOREIGN KEY ("subscribId") REFERENCES public."user"(id) NOT VALID;


--
-- Name: user_x_subscription user_x_subscription_userId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_x_subscription
    ADD CONSTRAINT "user_x_subscription_userId_fkey" FOREIGN KEY ("userId") REFERENCES public."user"(id) NOT VALID;


--
-- Name: TABLE category; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.category TO PUBLIC;


--
-- Name: TABLE category_x_charasterictic; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.category_x_charasterictic TO PUBLIC;


--
-- Name: TABLE characteristic; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.characteristic TO PUBLIC;


--
-- Name: TABLE comment; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.comment TO PUBLIC;


--
-- Name: TABLE post; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.post TO PUBLIC;


--
-- Name: TABLE post_x_category; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.post_x_category TO PUBLIC;


--
-- Name: TABLE post_x_characterictic; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.post_x_characterictic TO PUBLIC;


--
-- Name: TABLE "user"; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public."user" TO PUBLIC;


--
-- Name: TABLE user_x_category; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.user_x_category TO PUBLIC;


--
-- Name: TABLE user_x_subscription; Type: ACL; Schema: public; Owner: postgres
--

GRANT ALL ON TABLE public.user_x_subscription TO PUBLIC;


--
-- PostgreSQL database dump complete
--

