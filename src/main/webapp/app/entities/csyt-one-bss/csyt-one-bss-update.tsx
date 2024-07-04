import React, { useState, useEffect, useRef } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICsytOneBss } from 'app/shared/model/csyt-one-bss.model';
import { getEntity, updateEntity, createEntity, reset } from './csyt-one-bss.reducer';
import { Calendar } from 'react-date-range';
import '../csyt-one-bss/csyt-one-bss-update.scss';
import moment from 'moment';

export const CsytOneBssUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const csytOneBssEntity = useAppSelector(state => state.csytOneBss.entity);
  const loading = useAppSelector(state => state.csytOneBss.loading);
  const updating = useAppSelector(state => state.csytOneBss.updating);
  const updateSuccess = useAppSelector(state => state.csytOneBss.updateSuccess);

  const handleClose = () => {
    navigate('/csyt-one-bss');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
  }, []);

  useEffect(() => {
    if (!isNew && csytOneBssEntity) {
      setThoiGianCapNhat(csytOneBssEntity.thoigianCapnhat);
      setNgayKyHopDong(csytOneBssEntity.ngaykyHopdong);
      setHieuLucTu(csytOneBssEntity.hieulucTu);
      setHieuLucDen(csytOneBssEntity.hieulucDen);
    }
  }, [csytOneBssEntity]);

  const formatDate = (date, type = 'YYYY-MM-DD') => {
    return moment(date).format(type);
  };

  const [thoiGianCapNhat, setThoiGianCapNhat] = useState(null);
  const [ngayKyHopDong, setNgayKyHopDong] = useState(null);
  const [hieuLucTu, setHieuLucTu] = useState(null);
  const [hieuLucDen, setHieuLucDen] = useState(null);

  const [isOpenTGCN, setIsOpenTGCN] = useState(false);
  const [isOpenNKHD, setIsOpenNKHD] = useState(false);
  const [isOpenHLT, setIsOpenHLT] = useState(false);
  const [isOpenHLD, setIsOpenHLD] = useState(false);
  const refTGCN = useRef(null);
  const refNKHD = useRef(null);
  const refHLT = useRef(null);
  const refHLD = useRef(null);
  const pressOutsideTGCN = e => {
    if (refTGCN.current && !refTGCN.current.contains(e.target)) {
      setIsOpenTGCN(false);
    }
  };

  const pressOutsideNKHD = e => {
    if (refNKHD.current && !refNKHD.current.contains(e.target)) {
      setIsOpenNKHD(false);
    }
  };

  const pressOutsideHLT = e => {
    if (refHLT.current && !refHLT.current.contains(e.target)) {
      setIsOpenHLT(false);
    }
  };

  const pressOutsideHLD = e => {
    if (refHLD.current && !refHLD.current.contains(e.target)) {
      setIsOpenHLD(false);
    }
  };

  useEffect(() => {
    document.addEventListener('click', pressOutsideTGCN, true);
    document.addEventListener('click', pressOutsideNKHD, true);
    document.addEventListener('click', pressOutsideHLT, true);
    document.addEventListener('click', pressOutsideHLD, true);
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    if (values.id !== undefined && typeof values.id !== 'number') {
      values.id = Number(values.id);
    }
    if (values.trangthai !== undefined && typeof values.trangthai !== 'number') {
      values.trangthai = Number(values.trangthai);
    }
    values.lastModified = convertDateTimeToServer(values.lastModified);

    console.log(thoiGianCapNhat);
    console.log(ngayKyHopDong);
    console.log('ok' + csytOneBssEntity);
    const entity = {
      ...csytOneBssEntity,
      ...values,
      thoigianCapnhat: formatDate(thoiGianCapNhat),
      ngaykyHopdong: formatDate(ngayKyHopDong),
      hieulucTu: formatDate(hieuLucTu),
      hieulucDen: formatDate(hieuLucDen),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {
          lastModified: displayDefaultDateTime(),
        }
      : {
          ...csytOneBssEntity,
          lastModified: convertDateTimeFromServer(csytOneBssEntity.lastModified),
        };

  return (
    <div>
      <Row className="justify-content-center mb-4 mt-2">
        <Col md="9">
          <h2 id="csytApp.csytOneBss.home.createOrEditLabel" data-cy="CsytOneBssCreateUpdateHeading">
            <Translate contentKey="csytApp.csytOneBss.home.createOrEditLabel">Create or edit a CsytOneBss</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center" style={{ fontSize: '13px', fontWeight: '500' }}>
        <Col md="9">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity} className="row">
              {/* {!isNew ? (
                <ValidatedField
                  className="col-lg-3 col-md-6 col-sm-12"
                  name="id"
                  required
                  readOnly
                  id="csyt-one-bss-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null} */}
              <ValidatedField
                className="col-lg-3 col-md-6 col-sm-12"
                label={translate('csytApp.csytOneBss.maCsyt')}
                id="csyt-one-bss-maCsyt"
                name="maCsyt"
                data-cy="maCsyt"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                className="col-lg-3 col-md-6 col-sm-12"
                label={translate('csytApp.csytOneBss.maThuebao')}
                id="csyt-one-bss-maThuebao"
                name="maThuebao"
                data-cy="maThuebao"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                className="col-lg-3 col-md-6 col-sm-12"
                label={translate('csytApp.csytOneBss.maDuan')}
                id="csyt-one-bss-maDuan"
                name="maDuan"
                data-cy="maDuan"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />

              <ValidatedField
                className="col-lg-3 col-md-6 col-sm-12"
                label={translate('csytApp.csytOneBss.maTinh')}
                id="csyt-one-bss-maTinh"
                name="maTinh"
                data-cy="maTinh"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />

              <div className="col-lg-3 col-md-6 col-sm-12 ">
                <label className="mb-2 col-12">{translate('csytApp.csytOneBss.thoigianCapnhat')}</label>
                <div className="date">
                  <input
                    className="form-control"
                    value={thoiGianCapNhat ? formatDate(thoiGianCapNhat, 'DD/MM/yyyy') : ''}
                    onClick={() => setIsOpenTGCN(true)}
                  />
                  <div ref={refTGCN} className="calendar" style={{ display: isOpenTGCN ? 'flex' : 'none' }}>
                    <Calendar
                      showMonthAndYearPickers={false}
                      showDateDisplay={false}
                      months={1}
                      onChange={date => setThoiGianCapNhat(date)}
                      direction="vertical"
                      date={thoiGianCapNhat}
                    />
                  </div>
                </div>
              </div>

              <div className="col-lg-3 col-md-6 col-sm-12 ">
                <label className="mb-2 col-12">{translate('csytApp.csytOneBss.ngaykyHopdong')}</label>
                <div className="date">
                  <input
                    className="form-control"
                    value={ngayKyHopDong ? formatDate(ngayKyHopDong, 'DD/MM/yyyy') : ''}
                    onClick={() => setIsOpenNKHD(true)}
                  />
                  <div ref={refNKHD} className="calendar" style={{ display: isOpenNKHD ? 'flex' : 'none' }}>
                    <Calendar
                      showMonthAndYearPickers={false}
                      showDateDisplay={false}
                      months={1}
                      onChange={date => setNgayKyHopDong(date)}
                      direction="vertical"
                      date={ngayKyHopDong}
                    />
                  </div>
                </div>
              </div>

              <div className="col-lg-3 col-md-6 col-sm-12 mb-3">
                <label className="mb-2 col-12">{translate('csytApp.csytOneBss.hieulucTu')}</label>
                <div className="date">
                  <input
                    className="form-control"
                    value={hieuLucTu ? formatDate(hieuLucTu, 'DD/MM/yyyy') : ''}
                    onClick={() => setIsOpenHLT(true)}
                  />
                  <div ref={refHLT} className="calendar" style={{ display: isOpenHLT ? 'flex' : 'none' }}>
                    <Calendar
                      showMonthAndYearPickers={false}
                      showDateDisplay={false}
                      months={1}
                      onChange={date => setHieuLucTu(date)}
                      direction="vertical"
                      rangeColors={['#3d91ff', '#3ecf8e', '#fed14c']}
                      date={hieuLucTu}
                    />
                  </div>
                </div>
              </div>

              <div className="col-lg-3 col-md-6 col-sm-12">
                <label className="mb-2 col-12">{translate('csytApp.csytOneBss.hieulucDen')}</label>
                <div className="date">
                  <input
                    className="form-control"
                    value={hieuLucDen ? formatDate(hieuLucDen, 'DD/MM/yyyy') : ''}
                    onClick={() => setIsOpenHLD(true)}
                  />
                  <div ref={refHLD} className="calendar" style={{ display: isOpenHLD ? 'flex' : 'none' }}>
                    <Calendar
                      showMonthAndYearPickers={false}
                      showDateDisplay={false}
                      months={1}
                      onChange={date => setHieuLucDen(date)}
                      direction="vertical"
                      rangeColors={['#3d91ff', '#3ecf8e', '#fed14c']}
                      date={hieuLucDen}
                    />
                  </div>
                </div>
              </div>

              <ValidatedField
                className="col-lg-3 col-md-6 col-sm-12"
                label={translate('csytApp.csytOneBss.maSpdv')}
                id="csyt-one-bss-maSpdv"
                name="maSpdv"
                data-cy="maSpdv"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />
              <ValidatedField
                className="col-lg-3 col-md-6 col-sm-12"
                label={translate('csytApp.csytOneBss.maHopdong')}
                id="csyt-one-bss-maHopdong"
                name="maHopdong"
                data-cy="maHopdong"
                type="text"
                validate={{
                  maxLength: { value: 50, message: translate('entity.validation.maxlength', { max: 50 }) },
                }}
              />

              <ValidatedField
                className="col-lg-3 col-md-6 col-sm-12"
                label={translate('csytApp.csytOneBss.tenKhachhang')}
                id="csyt-one-bss-tenKhachhang"
                name="tenKhachhang"
                data-cy="tenKhachhang"
                type="text"
                validate={{
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />
              <ValidatedField
                className="col-lg-3 col-md-6 col-sm-12"
                label={translate('csytApp.csytOneBss.diachiKhachhang')}
                id="csyt-one-bss-diachiKhachhang"
                name="diachiKhachhang"
                data-cy="diachiKhachhang"
                type="text"
                validate={{
                  maxLength: { value: 255, message: translate('entity.validation.maxlength', { max: 255 }) },
                }}
              />

              <ValidatedField
                className="col-lg-3 col-md-6 col-sm-12"
                label={translate('csytApp.csytOneBss.trangthai')}
                id="trangthai"
                name="trangthai"
                data-cy="trangthai"
                type="select"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              >
                <option value="0" key="1">
                  Khởi tạo
                </option>
                <option value="1" key="2">
                  Gia hạn
                </option>
                <option value="2" key="3">
                  Hủy
                </option>
              </ValidatedField>

              <ValidatedField
                className="col-lg-3 col-md-6 col-sm-12"
                label={translate('csytApp.csytOneBss.mota')}
                id="csyt-one-bss-mota"
                name="mota"
                data-cy="mota"
                type="text"
                validate={{
                  maxLength: { value: 4000, message: translate('entity.validation.maxlength', { max: 4000 }) },
                }}
              />

              <Row className="mt-3">
                <Button
                  className="col-lg-2 col-md-5 col-sm-12 mt-1 me-1"
                  tag={Link}
                  id="cancel-save"
                  data-cy="entityCreateCancelButton"
                  to="/csyt-one-bss"
                  replace
                  color="info"
                >
                  <FontAwesomeIcon icon="arrow-left" />
                  &nbsp;
                  <Translate contentKey="entity.action.back">Back</Translate>
                </Button>

                <Button
                  className="col-lg-2 col-md-5 col-sm-12 mt-1"
                  color="primary"
                  id="save-entity"
                  data-cy="entityCreateSaveButton"
                  type="submit"
                  disabled={updating}
                >
                  <FontAwesomeIcon icon="save" />
                  &nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </Row>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default CsytOneBssUpdate;
