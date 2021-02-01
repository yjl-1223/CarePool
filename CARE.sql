-- MEMBER 테이블 생성
CREATE TABLE MEMBER (
	MEM_ID	VARCHAR2(20) NOT NULL UNIQUE,
	MEM_ROLE VARCHAR2(10) NULL,
	MEM_NAME VARCHAR2(10) NOT NULL,
	MEM_PWD	VARCHAR2(50)	NOT NULL,
	MEM_EMAIL VARCHAR2(50) NOT NULL,
	MEM_PHONE VARCHAR2(20) NOT NULL,
	MEM_ADDR VARCHAR2(100) NOT NULL,
	MEM_BIRTH VARCHAR2(20) NOT NULL,
	CREATE_DATE	DATE DEFAULT SYSDATE,
	MODIFY_DATE	DATE DEFAULT SYSDATE,
	STATUS VARCHAR2(1) DEFAULT 'Y' CHECK(STATUS IN('Y', 'N'))
);

-- MEMBER 테이블 코맨트
COMMENT ON COLUMN MEMBER.MEM_ID IS '회원 아이디';
COMMENT ON COLUMN MEMBER.MEM_ROLE IS '회원타입';
COMMENT ON COLUMN MEMBER.MEM_NAME IS '회원이름';
COMMENT ON COLUMN MEMBER.MEM_PWD IS '비밀번호';
COMMENT ON COLUMN MEMBER.MEM_EMAIL IS '이메일';
COMMENT ON COLUMN MEMBER.MEM_PHONE IS '휴대전화';
COMMENT ON COLUMN MEMBER.MEM_ADDR IS '회원주소';
COMMENT ON COLUMN MEMBER.MEM_BIRTH IS '생년월일';
COMMENT ON COLUMN MEMBER.CREATE_DATE IS '생성날짜';
COMMENT ON COLUMN MEMBER.MODIFY_DATE IS '수정날짜';
COMMENT ON COLUMN MEMBER.STATUS IS '상태값(Y/N)';

-- 관리자 정보 생성
INSERT INTO MEMBER VALUES (
    'admin',
    'ROLE,ADMIN',
    '관리자',
    '1234',
    'CAREPOOL@NAVER.COM',
    '01012345678',
    '서울시',
    '1993/00/00',
    SYSDATE,
    SYSDATE,
    DEFAULT
);

-- 임의의 회원 생성
--INSERT INTO MEMBER VALUES (
--    'asdf',
--    'ROLE,USER',
--    '홍길동',
--    '1234',
--    'CARE@NAVER.COM',
--    '01010202000',
--    '서울시',
--    '1999/10/20',
--    SYSDATE,
--    SYSDATE,
--    DEFAULT
--);

-- MEMBER 테이블은 별도로 시퀀스 필요없음
-- CREATE SEQUENCE SEQ_MEM_NO;

--------------------------------------------------------------------------------

-- 보호자 생성
CREATE TABLE GUARDIAN_PROFILE (
	GUARD_NO NUMBER	NOT NULL,
	GUARD_GEN VARCHAR2(3) NOT NULL,
	GUARD_PAT VARCHAR2(50),
	MEM_ID	VARCHAR2(100) NOT NULL
);

-- 시퀀스 생성
CREATE SEQUENCE SEQ_GUARD_NO;

-- 보호자 번호 PK등록
ALTER TABLE GUARDIAN_PROFILE ADD CONSTRAINT PK_GUARDIAN_PROFILE PRIMARY KEY (
	GUARD_NO
);

--ALTER TABLE GUARDIAN_PROFILE MODIFY GUARD_GEN CHAR(3) CHECK(GUARD_GEN IN('남','여'));

-- 회원 아이디 외래키 등록
ALTER TABLE GUARDIAN_PROFILE ADD CONSTRAINT FK_member_TO_guardian_profile FOREIGN KEY (
	MEM_ID
)
REFERENCES member (
	MEM_ID
);

-- 보호자프로필 코맨트 등록
COMMENT ON COLUMN GUARDIAN_PROFILE.GUARD_NO IS '보호자번호';
COMMENT ON COLUMN GUARDIAN_PROFILE.GUARD_GEN IS '성별';
COMMENT ON COLUMN GUARDIAN_PROFILE.GUARD_PAT IS '(보호자/환자)';

-- 보호자 프로필 생성
--INSERT INTO guardian_profile VALUES(
--    SEQ_GUARD_NO.NEXTVAL,
--    '남',
--    '환자',
--    'asdf'
--);

-- MEMBER 와 조인해서 정보 확인
SELECT MEM_PHONE, GUARD_GEN
FROM MEMBER M
JOIN guardian_profile G ON(M.MEM_ID = G.MEM_ID);

CREATE TABLE patient_details (
	pat_no NUMBER NOT NULL,
	guard_no NUMBER	NOT NULL,
    pat_place VARCHAR2(200) NOT NULL,
    pat_period VARCHAR2(200) NOT NULL,
    pat_hop_time VARCHAR2(200) NOT NULL,
	pat_name VARCHAR2(20) NOT NULL,
	pat_age	NUMBER NOT NULL,
	pat_gen	CHAR(3) NOT NULL CHECK(pat_gen IN('남','여')),
	pat_kg	NUMBER NOT NULL,
    pat_infect VARCHAR2(50) NOT NULL,
	pat_grade VARCHAR2(20) NOT NULL,
	pat_sanit VARCHAR2(50) NOT NULL,
	pat_paral VARCHAR2(50) NOT NULL,
	pat_move VARCHAR2(50) NOT NULL,
	pat_bed	VARCHAR2(50) NOT NULL,
	pat_cogdis VARCHAR2(50) NOT NULL,
	pat_bathroom VARCHAR2(50) NOT NULL,
	pat_bowel_mn VARCHAR2(50) NOT NULL,
	pat_ostomy	VARCHAR2(50) NOT NULL,
	pat_help_eat VARCHAR2(50) NOT NULL,
	pat_suction VARCHAR2(50) NOT NULL,
	pat_guard_gen CHAR(3) CHECK(pat_guard_gen IN('남','여')) NOT NULL,
	pat_etc	VARCHAR2(1000) NULL
);

CREATE SEQUENCE SEQ_pat_no;

ALTER TABLE patient_details ADD CONSTRAINT PK_PATIENT_DETAILS PRIMARY KEY (
	pat_no
);

--컬럼 추가 문
--ALTER TABLE patient_details ADD pat_hop_time DATE;
-- 2) 제약조건 추가/삭제
--      2-1) 제약조건 추가
--        PRIMARY KEY : ALTER TABLE 테이블명 ADD [CONSTRAINT 제약조건명] PRIMARY KEY(컬럼명);
--        FOREIGN KEY : ALTER TABLE 테이블명 ADD [CONSTRAINT 제약조건명] FOREIGN KEY(컬럼명) REFERENCES 참조할테이블명 [(컬럼명)];
--        UNIQUE      : ALTER TABLE 테이블명 ADD [CONSTRAINT 제약조건명] UNIQUE(컬럼명)
--        CHECK       : ALTER TABLE 테이블명 ADD [CONSTRAINT 제약조건명] CHECK(컬럼에대한 조건)
--        NOT NULL    : ALTER TABLE 테이블명 MODIFY 컬럼명 [CONSTRAINT 제약조건명] NOT NULL;

ALTER TABLE patient_details ADD CONSTRAINT FK_GUARD_TO_PAT_DETAIL FOREIGN KEY (
	guard_no
)
REFERENCES guardian_profile(
	guard_no
);

COMMENT ON COLUMN PATIENT_DETAILS.PAT_NO IS '환자번호';
COMMENT ON COLUMN PATIENT_DETAILS.guard_no IS '보호자번호';
COMMENT ON COLUMN PATIENT_DETAILS.pat_name IS '이름';
COMMENT ON COLUMN PATIENT_DETAILS.pat_age IS '나이';
COMMENT ON COLUMN PATIENT_DETAILS.pat_gen IS '성별';
COMMENT ON COLUMN PATIENT_DETAILS.pat_kg IS '몸무게';
COMMENT ON COLUMN PATIENT_DETAILS.pat_place IS '돌봄장소(자택/병원)';
COMMENT ON COLUMN PATIENT_DETAILS.pat_period IS '돌봄기간';
COMMENT ON COLUMN PATIENT_DETAILS.pat_hop_time IS '희망시간';
COMMENT ON COLUMN PATIENT_DETAILS.pat_grade IS '요양등급';
COMMENT ON COLUMN PATIENT_DETAILS.pat_infect IS '감염성질환';
COMMENT ON COLUMN PATIENT_DETAILS.pat_sanit IS '개인위생';
COMMENT ON COLUMN PATIENT_DETAILS.pat_paral IS '마비상태';
COMMENT ON COLUMN PATIENT_DETAILS.pat_move IS '거동상태';
COMMENT ON COLUMN PATIENT_DETAILS.pat_bed IS '욕창환자여부(예/아니요)';
COMMENT ON COLUMN PATIENT_DETAILS.pat_cogdis IS '인지장애여부';
COMMENT ON COLUMN PATIENT_DETAILS.pat_bathroom IS '화장실이동';
COMMENT ON COLUMN PATIENT_DETAILS.pat_bowel_mn IS '배변도구';
COMMENT ON COLUMN PATIENT_DETAILS.pat_ostomy IS '장루설치여부';
COMMENT ON COLUMN PATIENT_DETAILS.pat_help_eat IS '식사도움여부';
COMMENT ON COLUMN PATIENT_DETAILS.pat_suction IS '석션사용여부';
COMMENT ON COLUMN PATIENT_DETAILS.pat_guard_gen IS '우대성별';
COMMENT ON COLUMN PATIENT_DETAILS.pat_etc IS '기타사항';

--INSERT INTO PATIENT_DETAILS VALUES (
--     SEQ_PAT_NO.NEXTVAL,
--     1,
--     '자택-서울시 송파구 잠실동 xx빌라 307호',
--     '2021-01-10~2021-11-27',
--     '오전-11시-15분',
--     '아파요',
--     77,
--     '남',
--     45,
--     'VRE, 독감, 아무거나 적어주세요',
--     '등급신청중',
--     '침대에서 도움 필요',
--     '편마비',
--     '홀로 가능',
--     '아니요',
--     '섬망',
--     '스스로 이동가능',
--     '없음',
--     '아니요',
--     '도움 필요',
--     '사용안함',
--     '남',
--     '안녕하세요 잘부탁드립니다'
--);

-- 요양보호사 프로필
CREATE TABLE caregiver_profile (
	care_no 	    NUMBER	        NOT NULL,
    care_gen        CHAR(3) NOT NULL CHECK (CARE_GEN IN('남','여')),
	care_license	VARCHAR(100)	NULL,
	care_years	    VARCHAR(100)	NULL,
	care_history	VARCHAR(100)	NULL,
	care_plus	    VARCHAR(100)	NULL,
	care_time	    VARCHAR(100)	NULL,
	care_place	    VARCHAR2(100)	NULL,
	care_sal	    VARCHAR2(20)    NULL,
	care_intro	    VARCHAR2(200)	NULL,
	mem_id	        VARCHAR2(20)	NOT NULL
);

ALTER TABLE caregiver_profile ADD CONSTRAINT PK_CAREGIVER_PROFILE PRIMARY KEY (
	care_no
);

ALTER TABLE caregiver_profile ADD CONSTRAINT FK_member_TO_caregiver_profile FOREIGN KEY (
	MEM_ID
) 
REFERENCES MEMBER (
	MEM_ID
);

-- 시퀀스 생성
CREATE SEQUENCE SEQ_care_no;

-- 코멘트
COMMENT ON COLUMN CAREGIVER_PROFILE.CARE_NO IS '요양사번호';
COMMENT ON COLUMN CAREGIVER_PROFILE.CARE_LICENSE IS '자격증';
COMMENT ON COLUMN CAREGIVER_PROFILE.CARE_YEARS IS '경력';
COMMENT ON COLUMN CAREGIVER_PROFILE.CARE_HISTORY IS '경력사항 상세입력';
COMMENT ON COLUMN CAREGIVER_PROFILE.CARE_PLUS IS '장점';
COMMENT ON COLUMN CAREGIVER_PROFILE.CARE_TIME IS '희망근무시간';
COMMENT ON COLUMN CAREGIVER_PROFILE.CARE_PLACE IS '희망근무위치';
COMMENT ON COLUMN CAREGIVER_PROFILE.CARE_SAL IS '희망급여';
COMMENT ON COLUMN CAREGIVER_PROFILE.CARE_INTRO IS '자기소개';

-- 케어희망환자
CREATE TABLE patient_wanted (
	wanted_grade	VARCHAR2(40 BYTE)	        NOT NULL,
	care_no	        NUMBER	                    NOT NULL,
	wanted_gen	    CHAR(3) NULL CHECK (WANTED_GEN IN('남','여')),
	wanted_age	    VARCHAR2(20 BYTE)	        NULL,
	wanted_ill	    VARCHAR(100)	            NULL
);

ALTER TABLE patient_wanted ADD CONSTRAINT PK_PATIENT_WANTED PRIMARY KEY (
	wanted_grade,
	care_no
);

--ALTER TABLE patient_wanted ADD CONSTRAINT FK_caregiver_profile_TO_patient_wanted FOREIGN KEY (
--	care_no
--)
--REFERENCES caregiver_profile (
--	care_no
--);

COMMENT ON COLUMN PATIENT_WANTED.WANTED_GRADE IS '등급';
COMMENT ON COLUMN PATIENT_WANTED.WANTED_GEN IS '희망성별';
COMMENT ON COLUMN PATIENT_WANTED.WANTED_AGE IS '희망나이';
COMMENT ON COLUMN PATIENT_WANTED.WANTED_ILL IS '희망질환';

-- 요양보호사 이미지
CREATE TABLE care_image (
	img_no	       NUMBER	    NOT NULL,
	care_no    	   NUMBER	    NOT NULL,
	img_date	   DATE     	DEFAULT SYSDATE,
	img_path	   VARCHAR(100)	NULL,
	img_name_org   VARCHAR(100)	NULL,
	img_name_sav   VARCHAR(100)	NULL
);

ALTER TABLE care_image ADD CONSTRAINT PK_CARE_IMAGE PRIMARY KEY (
	img_no,
	care_no
);

--ALTER TABLE care_image ADD CONSTRAINT FK_caregiver_profile_TO_care_image FOREIGN KEY (
--	care_no
--)
--REFERENCES caregiver_profile (
--	care_no
--);

COMMENT ON COLUMN CARE_IMAGE.IMG_NO IS '파일번호';
COMMENT ON COLUMN CARE_IMAGE.IMG_DATE IS '등록일';
COMMENT ON COLUMN CARE_IMAGE.IMG_PATH IS '저장위치';
COMMENT ON COLUMN CARE_IMAGE.IMG_NAME_ORG IS '원본파일이름';
COMMENT ON COLUMN CARE_IMAGE.IMG_NAME_SAV IS '저장파일이름';

--------------------------------------------------------------------------------

---- 매치 쿼리문 --
--
---- MATCH 테이블 생성
--CREATE TABLE MATCH (
--	mat_no number NOT NULL,
--	mat_place varchar2(100)	NOT NULL,
--    mat_date_sta date DEFAULT SYSDATE,
--	mat_date_end date DEFAULT SYSDATE,
--	mat_rq varchar2(100) NULL,
--	care_no number NOT NULL,
--	guard_no number	NOT NULL,    
--    CONSTRAINT PK_MATCH PRIMARY KEY(mat_no)
--);
--
--CREATE SEQUENCE SEQ_MAT_NO;
--
--COMMENT ON COLUMN MATCH.mat_no IS '매칭번호';
--COMMENT ON COLUMN MATCH.mat_place IS '돌봄위치';
--COMMENT ON COLUMN MATCH.mat_date_sta IS '시작날짜';
--COMMENT ON COLUMN MATCH.mat_date_end IS '종료날짜';
--COMMENT ON COLUMN MATCH.mat_rq IS '요구사항';
--COMMENT ON COLUMN MATCH.care_no IS '요양사번호';
--COMMENT ON COLUMN MATCH.guard_no IS '보호자번호';
--
----------------------------------------------------------------------------------
--
---- MATCH_DETAILS 테이블 생성
--CREATE TABLE MATCH_DETAILS (
--	det_no varchar2(100) NOT NULL,
--	det_date_fix date DEFAULT SYSDATE,
--	mat_no number NOT NULL,
--    CONSTRAINT PK_MATCH_DETAILS PRIMARY KEY(det_no)
--);
--
--COMMENT ON COLUMN MATCH_DETAILS.det_no IS '매칭내역번호';
--COMMENT ON COLUMN MATCH_DETAILS.det_date_fix IS '매칭확정날짜';
--COMMENT ON COLUMN MATCH_DETAILS.mat_no IS '매칭번호';
--
--CREATE SEQUENCE SEQ_DET_NO;
--
----------------------------------------------------------------------------------
--
---- CERTIFICATION 테이블 생성
--CREATE TABLE CERTIFICATION (
--	cert_no number NOT NULL,
--	cert_file varchar2(20) NULL,
--	cert_text varchar2(100) NULL,
--	cert_time date DEFAULT SYSDATE,
--	mat_no number NOT NULL,
--    CONSTRAINT PK_CERTIFICATION PRIMARY KEY(cert_no)
--);
--
--COMMENT ON COLUMN CERTIFICATION.cert_no IS '인증번호';
--COMMENT ON COLUMN CERTIFICATION.cert_file IS '첨부파일';
--COMMENT ON COLUMN CERTIFICATION.cert_text IS '텍스트';
--COMMENT ON COLUMN CERTIFICATION.cert_time IS '보낸시간';
--COMMENT ON COLUMN CERTIFICATION.mat_no IS '매칭번호';
--
--CREATE SEQUENCE SEQ_CERT_NO;
--
----------------------------------------------------------------------------------
--
----FOREIGN KEY 추가
--ALTER TABLE MATCH_DETAILS ADD FOREIGN KEY(mat_no) REFERENCES MATCH;
--
--ALTER TABLE CERTIFICATION ADD FOREIGN KEY(mat_no) REFERENCES MATCH;
--
--
--
--ALTER TABLE MATCH ADD FOREIGN KEY(care_no) REFERENCES GUARDIAN_PROFILE;
--
---- 파일 병합 후 실행
----ALTER TABLE MATCH ADD FOREIGN KEY(guard_no) REFERENCES CAREGIVER_PROFILE;
--
----------------------------------------------------------------------------------
--
---- 매칭 검색 쿼리문 -----
--
----매칭정보검색
----DROP TABLE IF EXISTS match_seek;
--
--CREATE TABLE MATCH_SEEK (
--	SEEK_NO	NUMBER	NOT NULL,
--    SEEK_TIME DATE,
--	SEEK_GEN CHAR(3) NULL  CHECK(SEEK_GEN IN('남','여')),
--	SEEK_LICENSE VARCHAR2(10) NULL,
--	SEEK_YEARS VARCHAR2(10) NULL,
--    SEEK_AREA VARCHAR2(10) NULL,
--	SEEK_SAL NUMBER NULL,
--	SEEK_GUARD_NO NUMBER NOT NULL
--);
--
--
--
----DROP TABLE MATCH_SEEK;
--
--COMMENT ON COLUMN  MATCH_SEEK.SEEK_NO IS '검색번호';
--COMMENT ON COLUMN  MATCH_SEEK.SEEK_TIME IS '돌봄시간';
--COMMENT ON COLUMN  MATCH_SEEK.SEEK_GEN IS '요양보호사성별';
--COMMENT ON COLUMN  MATCH_SEEK.SEEK_LICENSE IS '자격증유무';
--COMMENT ON COLUMN  MATCH_SEEK.SEEK_YEARS IS '경력사항';
--COMMENT ON COLUMN  MATCH_SEEK.SEEK_AREA IS '희망근무지역';
--COMMENT ON COLUMN  MATCH_SEEK.SEEK_SAL IS '급여지급';
--COMMENT ON COLUMN  MATCH_SEEK.SEEK_GUARD_NO IS '검색보호자번호';
--
--
----COMMENT ON TABLE MATCH_SEEK.SEEK_YEARS IS ''; 
--
--ALTER TABLE MATCH_SEEK ADD CONSTRAINT PK_MATCH_SEEK PRIMARY KEY (
--    SEEK_NO
--);
--
--ALTER TABLE match_seek ADD CONSTRAINT FK_GUARD_PRF_TO_MAT_SEEK FOREIGN KEY (
--     SEEK_GUARD_NO
--)
--REFERENCES GUARDIAN_PROFILE(
--   GUARD_NO
--);
--
---- 문의프로필
--
----DROP TABLE IF EXISTS profile_qs;
--
--CREATE TABLE PROFILE_QS(
--	QS_NO	NUMBER	NOT NULL,
--	CARE_NO	NUMBER	NOT NULL,
--    SEEK_NO	NUMBER	NOT NULL
--);
--
--COMMENT ON COLUMN PROFILE_QS.QS_NO IS '문의번호';
--COMMENT ON COLUMN PROFILE_QS.CARE_NO IS '요양사번호';
--COMMENT ON COLUMN PROFILE_QS.SEEK_NO IS '검색번호';
--
--ALTER TABLE PROFILE_QS ADD CONSTRAINT PK_PROFILE_QS PRIMARY KEY (
--	QS_NO
--);
--
--ALTER TABLE PROFILE_QS ADD CONSTRAINT FK_CAR_PRF_PROFILE_QS FOREIGN KEY (
--	CARE_NO
--)
--REFERENCES CAREGIVER_PROFILE (
--   CARE_NO
--);
--
--ALTER TABLE PROFILE_QS ADD CONSTRAINT FK_MATCH_SEEK_TO_PROFILE_QS_1 FOREIGN KEY (
--   SEEK_NO
--)
--REFERENCES MATCH_SEEK (
--   SEEK_NO
--);
--
----------------------------------------------------------------------------------
--
---- ALTER TABLE GUARDIAN_PROFILE DROP CONSTRAINT <CONSTRAINT_NAME>;

----------------------------------------------------------------------------------

--SELECT M.MEM_NAME, M.MEM_ID 
--FROM MEMBER M
--JOIN CAREGIVER_PROFILE C ON (M.MEM_ID = C.MEM_ID)
--WHERE M.STATUS = 'Y' AND
--    M.MEM_ROLE = 'caregiver' AND 
--    C.CARE_TIME IN ('주간') AND 
--    C.CARE_GEN IN ('남') AND 
--    C.CARE_LICENSE IN ('요양보호사,간호사') AND 
--    C.CARE_YEARS IN ('10년 이상') AND
--    C.CARE_PLACE IN ('서울특별시 용산구') AND
--    C.CARE_SAL IN ('협의가능');

--    C.CARE_LICENSE IN ('요양보호사,간호사') AND 
--
--    C.CARE_LICENSE IN ('요양보호사','간호사') AND 

--
--SELECT M.MEM_NAME, M.MEM_ID 
--FROM MEMBER M
--JOIN CAREGIVER_PROFILE C ON (M.MEM_ID = C.MEM_ID)
--WHERE M.STATUS = 'Y' AND 
--M.MEM_ROLE = 'caregiver' AND
--REGEXP_LIKE (C.CARE_LICENSE, '(간호사|요양보호사)');
--
--SELECT M.MEM_NAME, M.MEM_ID 
--FROM MEMBER M 
--JOIN CAREGIVER_PROFILE C ON (M.MEM_ID = C.MEM_ID)
--WHERE M.STATUS = 'Y' AND 
--M.MEM_ROLE = 'caregiver' AND 
--				+ 		"REGEXP_LIKE (C.CARE_TIME, '(?time)')");
--//				+ 		"C.CARE_GEN REGEXP_LIKE (?gender) AND "
--//				+ 		"C.CARE_LICENSE REGEXP_LIKE (?qual) AND "
--//				+ 		"C.CARE_YEARS REGEXP_LIKE (?years) AND "
--//				+ 		"C.CARE_PLACE REGEXP_LIKE (?addr) AND "
--//				+ 		"C.CARE_SAL REGEXP_LIKE (?pay)");


--SELECT M.MEM_NAME, M.MEM_ID FROM MEMBER M JOIN CAREGIVER_PROFILE C ON (M.MEM_ID = C.MEM_ID) WHERE M.STATUS = 'Y' AND M.MEM_ROLE = 'caregiver'  AND REGEXP_LIKE (C.CARE_TIME, '(주간)');
--
--SELECT M.MEM_NAME, M.MEM_ID 
--FROM MEMBER M 
--JOIN CAREGIVER_PROFILE C ON (M.MEM_ID = C.MEM_ID) 
--WHERE M.STATUS = 'Y' AND M.MEM_ROLE = 'caregiver' 
--AND REGEXP_LIKE (C.CARE_TIME, '(주간)') 
--AND REGEXP_LIKE (C.CARE_GEN, '(남)') 
--AND REGEXP_LIKE (C.CARE_LICENSE, '(요양보호사|간호사)')
--AND REGEXP_LIKE (C.CARE_YEARS, '(10년 이상)') 
--AND REGEXP_LIKE (C.CARE_PLACE, '(서울특별시 용산구)')
--AND REGEXP_LIKE (C.CARE_SAL, '(협의가능)');

--------------------------------------------------------------------------------

--ALTER SYS_C007455 ON DELETE CASCADE;

--ALTER TABLE CAREGIVER_PROFILE
--DROP CONSTRAINT SYS_C007527;

--ALTER TABLE CAREGIVER_PROFILE
--ADD FOREIGN KEY(MEM_ID) REFERENCES MEMBER(MEM_ID) ON DELETE CASCADE;

--ALTER TABLE GUARDIAN_PROFILE
--DROP CONSTRAINT FK_MEMBER_TO_GUARDIAN_PROFILE;

--ALTER TABLE GUARDIAN_PROFILE
--ADD FOREIGN KEY(MEM_ID) REFERENCES MEMBER(MEM_ID) ON DELETE CASCADE;

--------------------------------------------------------------------------------

CREATE TABLE rec_msg (
	rec_no	number	NOT NULL,
    send_id	varchar2(50)	NOT NULL,
	rec_body	varchar2(200)	NULL,
    rec_date	date	DEFAULT SYSDATE,
	mem_id	varchar2(50)	NOT NULL,
    STATUS VARCHAR2(1) DEFAULT 'Y' CHECK(STATUS IN('Y','N'))
);



CREATE SEQUENCE SEQ_REC_NO;

ALTER TABLE rec_msg ADD CONSTRAINT PK_REC_MSG PRIMARY KEY (
	rec_no
);

ALTER TABLE rec_msg ADD CONSTRAINT FK_member_TO_rec_msg_1 FOREIGN KEY (
	mem_id
)
REFERENCES member (
	mem_id
);


CREATE TABLE send_msg (
	send_no	number	NOT NULL,
	rec_id	 VARCHAR2(50)	NOT NULL,
	send_body	varchar(200)	NULL,
	send_date	date DEFAULT SYSDATE,
	mem_id	varchar2(50)	NOT NULL,
    STATUS VARCHAR2(1) DEFAULT 'Y' CHECK(STATUS IN('Y','N'))
);

ALTER TABLE send_msg ADD CONSTRAINT PK_SEND_MSG PRIMARY KEY (send_no);

ALTER TABLE send_msg ADD CONSTRAINT FK_member_TO_send_msg FOREIGN KEY (mem_id) REFERENCES member (mem_id);

CREATE SEQUENCE SEQ_SEND_NO;


-- 받은 쪽지 이미지
CREATE TABLE rec_image (
   REC_IMG_NO   NUMBER   NOT NULL,
   REC_IMG_PATH   VARCHAR2(100)   NULL,
   REC_IMG_NAME_ORG   VARCHAR2(50)   NULL,
   REC_IMG_NAME_SAV   VARCHAR2(50)   NULL,
    rec_no   number   NOT NULL
);

--drop table rec_image;

CREATE SEQUENCE SEQ_REC_IMAGE_NO;

ALTER TABLE rec_image ADD CONSTRAINT PK_REC_IMAGE PRIMARY KEY (
   REC_IMG_NO
);

COMMENT ON COLUMN rec_image.REC_IMG_NO IS '받은이미지번호';
COMMENT ON COLUMN rec_image.REC_IMG_PATH IS '저장위치';
COMMENT ON COLUMN rec_image.REC_IMG_NAME_ORG IS '받은원본파일이름';
COMMENT ON COLUMN rec_image.REC_IMG_NAME_SAV IS '받은저장파일이름';

-- 보낸 쪽지 이미지
CREATE TABLE send_image (
   SEND_IMG_NO   NUMBER   NOT NULL,
   SEND_IMG_PATH   VARCHAR2(100)   NULL,
   SEND_IMG_NAME_ORG   VARCHAR2(50)   NULL,
   SEND_IMG_NAME_SAV   VARCHAR2(50)   NULL,
   send_no   number   NOT NULL
);

CREATE SEQUENCE SEQ_SEND_IMAGE_NO;

--drop table send_image;

ALTER TABLE send_image ADD CONSTRAINT PK_SEND_IMAGE PRIMARY KEY (
   SEND_IMG_NO
);

COMMENT ON COLUMN send_image.SEND_IMG_NO IS '보낸이미지번호';
COMMENT ON COLUMN send_image.SEND_IMG_PATH IS '저장위치';
COMMENT ON COLUMN send_image.SEND_IMG_NAME_ORG IS '보낸원본파일이름';
COMMENT ON COLUMN send_image.SEND_IMG_NAME_SAV IS '보낸저장파일이름';

--------------------------------------------------------------------------------

CREATE SEQUENCE SEQ_IMG_NO;

-- SEQUENCE
--SEQ_CARE_NO
--SEQ_GUARD_NO
--SEQ_IMG_NO
--SEQ_PAT_NO
--SEQ_REC_IMAGE_NO
--SEQ_REC_NO
--SEQ_SEND_IMAGE_NO
--SEQ_SEND_NO

COMMIT;

SELECT IMG_NAME_SAV
FROM CARE_IMAGE I
JOIN CAREGIVER_PROFILE P ON (I.CARE_NO = P.CARE_NO)
WHERE P.CARE_NO = 10;

SELECT M.MEM_NAME, M.MEM_ID, C.CARE_NO 
FROM MEMBER M 
JOIN CAREGIVER_PROFILE C ON (M.MEM_ID = C.MEM_ID) 
WHERE M.STATUS = 'Y' AND 
M.MEM_ROLE = 'caregiver';
