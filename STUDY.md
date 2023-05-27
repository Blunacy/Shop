# EntityMapping_Annotation

@Entity 클래스를 엔티티로 선언

@Table 엔티티와 매핑할 테이블을 지정

@Id 테이블의 기본키에 사용할 속성을 지정

@GeneratedValue 키 값을 생성하는 전략 명시

@Column 필드와 컬럼 매핑

@Lob BLOB, CLOB 타입 매핑
* CLOB 사이즈가 큰 데이터를 외부 파일로 저장하기 위한 데이터 타입 문자형 대용량 파일 저장용
* BLOB 바이너리 데이터를 DB 외부에 저장하기 위한 타입 이미지 / 사운드 / 비디오

@CreationTimestamp insert 시 시간 자동 저장

@UpdateTimestamp update 시 시간 자동 저장

@Enumerated enum 타입 매핑

@Transient 해당 필드 데이터베이스 매핑 무시

@Temporal 날자 타입 매핑

@CreateDate 엔티티가 생성되어 저장될 때 시간 자동 저장

@LastModifiedDate 조회한 엔티티의 값을 변경할 때 시간 자동 저장

# Column_Annotation

| 속성                    | 설명                                                                                                         | 기본값       |
|-----------------------|------------------------------------------------------------------------------------------------------------|-----------|
| name                  | 필드와 매핑할 컬럼의 이름 설정                                                                                          | 객체의 필드 이름 |
| unique(DDL)           | 유니크 제약 조건 설정                                                                                               |           |
| insertable            | insert 가능 여부                                                                                               | true      |
| updatable             | update 가능 여부                                                                                               | true      |
| length                | String 타입의 문자 길이 제약조건 설정                                                                                   | 255       |
| nullable(DDL)         | null 값의 허용 여부 설정 false 설정 시 DDL 생성 시에 not null 제약조건 추가                                                     |           |
| columnDefinition      | 데이터베이스 컬럼 정보 직접 기술                                                                                         |           |
| precision, scale(DDL) | BigDecimal 타입에서 사용(BigInteger 가능) precision은 소수점을 포함한 전체 자리수이고, scale은 소수점 자리수. Double과 float 타입에는 적용되지 않음 |           |

DDL_Data Definition Language : 테이블, 스키마, 인덱스, 뷰, 도메인을 정의, 변경, 제거할 때 사용하는 언어

# GeneratedValue_Annotation
| 생성 전략                         | 설명                              |
|-------------------------------|---------------------------------|
| GenerationType.AUTO (default) | JPA 구현체가 자동으로 생성 전략 결정          |
| GenerationType.IDENTITY       | 기본키 생성을 데이터베이스에 위임              |
| GenerationType.SEQUENCE       | 데이터베이스 시퀀스 오브젝트를 이용한 기본키 생성     |
| GenerationType.TABLE          | 키 생성용 테이블 사용 @TableGenerator 필요 |