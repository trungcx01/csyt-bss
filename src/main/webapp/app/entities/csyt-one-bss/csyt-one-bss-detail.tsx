import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './csyt-one-bss.reducer';

export const CsytOneBssDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const csytOneBssEntity = useAppSelector(state => state.csytOneBss.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="csytOneBssDetailsHeading">
          <Translate contentKey="csytApp.csytOneBss.detail.title">CsytOneBss</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{csytOneBssEntity.id}</dd>
          <dt>
            <span id="maCsyt">
              <Translate contentKey="csytApp.csytOneBss.maCsyt">Ma Csyt</Translate>
            </span>
          </dt>
          <dd>{csytOneBssEntity.maCsyt}</dd>
          <dt>
            <span id="maThuebao">
              <Translate contentKey="csytApp.csytOneBss.maThuebao">Ma Thuebao</Translate>
            </span>
          </dt>
          <dd>{csytOneBssEntity.maThuebao}</dd>
          <dt>
            <span id="maDuan">
              <Translate contentKey="csytApp.csytOneBss.maDuan">Ma Duan</Translate>
            </span>
          </dt>
          <dd>{csytOneBssEntity.maDuan}</dd>
          <dt>
            <span id="trangthai">
              <Translate contentKey="csytApp.csytOneBss.trangthai">Trangthai</Translate>
            </span>
          </dt>
          <dd>{csytOneBssEntity.trangthai}</dd>
          <dt>
            <span id="thoigianCapnhat">
              <Translate contentKey="csytApp.csytOneBss.thoigianCapnhat">Thoigian Capnhat</Translate>
            </span>
          </dt>
          <dd>
            {csytOneBssEntity.thoigianCapnhat ? (
              <TextFormat value={csytOneBssEntity.thoigianCapnhat} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="maTinh">
              <Translate contentKey="csytApp.csytOneBss.maTinh">Ma Tinh</Translate>
            </span>
          </dt>
          <dd>{csytOneBssEntity.maTinh}</dd>
          <dt>
            <span id="maSpdv">
              <Translate contentKey="csytApp.csytOneBss.maSpdv">Ma Spdv</Translate>
            </span>
          </dt>
          <dd>{csytOneBssEntity.maSpdv}</dd>
          <dt>
            <span id="maHopdong">
              <Translate contentKey="csytApp.csytOneBss.maHopdong">Ma Hopdong</Translate>
            </span>
          </dt>
          <dd>{csytOneBssEntity.maHopdong}</dd>
          <dt>
            <span id="ngaykyHopdong">
              <Translate contentKey="csytApp.csytOneBss.ngaykyHopdong">Ngayky Hopdong</Translate>
            </span>
          </dt>
          <dd>
            {csytOneBssEntity.ngaykyHopdong ? (
              <TextFormat value={csytOneBssEntity.ngaykyHopdong} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="hieulucTu">
              <Translate contentKey="csytApp.csytOneBss.hieulucTu">Hieuluc Tu</Translate>
            </span>
          </dt>
          <dd>
            {csytOneBssEntity.hieulucTu ? (
              <TextFormat value={csytOneBssEntity.hieulucTu} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="hieulucDen">
              <Translate contentKey="csytApp.csytOneBss.hieulucDen">Hieuluc Den</Translate>
            </span>
          </dt>
          <dd>
            {csytOneBssEntity.hieulucDen ? (
              <TextFormat value={csytOneBssEntity.hieulucDen} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="tenKhachhang">
              <Translate contentKey="csytApp.csytOneBss.tenKhachhang">Ten Khachhang</Translate>
            </span>
          </dt>
          <dd>{csytOneBssEntity.tenKhachhang}</dd>
          <dt>
            <span id="diachiKhachhang">
              <Translate contentKey="csytApp.csytOneBss.diachiKhachhang">Diachi Khachhang</Translate>
            </span>
          </dt>
          <dd>{csytOneBssEntity.diachiKhachhang}</dd>
          <dt>
            <span id="mota">
              <Translate contentKey="csytApp.csytOneBss.mota">Mota</Translate>
            </span>
          </dt>
          <dd>{csytOneBssEntity.mota}</dd>
          <dt>
            <span id="lastUser">
              <Translate contentKey="csytApp.csytOneBss.lastUser">Last User</Translate>
            </span>
          </dt>
          <dd>{csytOneBssEntity.lastUser}</dd>
          <dt>
            <span id="lastModified">
              <Translate contentKey="csytApp.csytOneBss.lastModified">Last Modified</Translate>
            </span>
          </dt>
          <dd>
            {csytOneBssEntity.lastModified ? (
              <TextFormat value={csytOneBssEntity.lastModified} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
        </dl>
        <Button tag={Link} to="/csyt-one-bss" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/csyt-one-bss/${csytOneBssEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CsytOneBssDetail;
