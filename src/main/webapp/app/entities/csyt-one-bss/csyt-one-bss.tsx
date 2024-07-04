import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, TextFormat, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';
import { translate } from 'react-jhipster';
import { getEntities } from './csyt-one-bss.reducer';
import CsytOneBssFilterComponent from './csyt-one-bss-filter';
import axios from 'axios';

export const CsytOneBss = () => {
  const dispatch = useAppDispatch();
  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const csytOneBssList = useAppSelector(state => state.csytOneBss.entities);
  const [list, setList] = useState([]);
  const loading = useAppSelector(state => state.csytOneBss.loading);

  const trangthai = ['Khởi tạo', 'Gia hạn', 'Hủy'];

  const getAllEntities = () => {
    dispatch(
      getEntities({
        sort: `${sortState.sort},${sortState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?sort=${sortState.sort},${sortState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
    setList(csytOneBssList);
  }, [sortState.order, sortState.sort]);

  const [filter, setFilter] = useState({
    maCsyt: '',
    maThuebao: '',
    maDuan: '',
    trangthai: '',
    thoigianCapnhatStart: '',
    thoigianCapnhatEnd: '',
    maTinh: '',
    maSpdv: '',
    maHopdong: '',
    ngaykyHopdongStart: '',
    ngaykyHopdongEnd: '',
  });
  const onFilterChange = filter => {
    setFilter(filter);
  };

  const sort = p => () => {
    if (Object.values(filter).filter(val => val === '').length === Object.values(filter).length) {
      setSortState({
        ...sortState,
        order: sortState.order === ASC ? DESC : ASC,
        sort: p,
      });
    }
  };

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = sortState.sort;
    const order = sortState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    } else {
      return order === ASC ? faSortUp : faSortDown;
    }
  };

  const getFilterListCsyt = async () => {
    const params = {
      'maCsyt.contains': filter.maCsyt,
      'maThuebao.contains': filter.maThuebao,
      'maDuan.contains': filter.maDuan,
      'trangthai.equals': filter.trangthai,
      'thoigianCapnhat.greaterThanOrEqual': filter.thoigianCapnhatStart,
      'thoigianCapnhat.lessThanOrEqual': filter.thoigianCapnhatEnd,
      'maTinh.contains': filter.maTinh,
      'maSpdv.contains': filter.maSpdv,
      'maHopdong.contains': filter.maHopdong,
      'ngaykyHopdong.greaterThanOrEqual': filter.ngaykyHopdongStart,
      'ngaykyHopdong.lessThanOrEqual': filter.ngaykyHopdongEnd,
    };
    try {
      const res = await axios.get('http://localhost:9000/api/csyt-one-bsses', { params });
      if (res && res.data) {
        setList(res.data);
        console.log(res.data);
      }
    } catch (error) {
      console.log('error', error);
    }
  };

  useEffect(() => {
    getFilterListCsyt();
  }, [filter]);

  return (
    <div style={{ marginBottom: '90px' }}>
      <div className="header row mb-4">
        <h3 id="csyt-one-bss-heading" data-cy="CsytOneBssHeading" className="col-lg-8 col-sm-12">
          Csyt One Bsses
        </h3>
        <div className="col-lg-4 col-sm-12 d-flex">
          <Button className="me-2 col-lg-7" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> {translate('csytApp.csytOneBss.home.refreshListLabel')}
          </Button>
          <Link
            to="/csyt-one-bss/new"
            className="col-lg-5 btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp; {translate('csytApp.csytOneBss.home.createLabel')}
          </Link>
        </div>
      </div>

      <div className="filter row mb-4">
        <CsytOneBssFilterComponent onFilterChange={onFilterChange} />
      </div>
      <p className="ms-1 mt-4 mb-4" style={{ fontSize: '13px' }}>
        Có <b>{list.length}</b> kết quả phù hợp.
      </p>
      <div className="table-responsive" style={{ marginTop: '30px' }}>
        {list && list.length > 0 ? (
          <Table responsive bordered hover striped>
            <thead>
              <tr className="text-center align-middle" style={{ fontSize: '15.5px' }}>
                <th className="hand" onClick={sort('id')}>
                  {translate('csytApp.csytOneBss.id')} <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('maCsyt')}>
                  {translate('csytApp.csytOneBss.maCsyt')}
                  <FontAwesomeIcon icon={getSortIconByFieldName('maCsyt')} />
                </th>
                <th className="hand" onClick={sort('maThuebao')}>
                  {translate('csytApp.csytOneBss.maThuebao')} <FontAwesomeIcon icon={getSortIconByFieldName('maThuebao')} />
                </th>
                <th className="hand" onClick={sort('maDuan')}>
                  {translate('csytApp.csytOneBss.maDuan')} <FontAwesomeIcon icon={getSortIconByFieldName('maDuan')} />
                </th>
                <th className="hand" onClick={sort('trangthai')}>
                  {translate('csytApp.csytOneBss.trangthai')} <FontAwesomeIcon icon={getSortIconByFieldName('trangthai')} />
                </th>
                <th className="hand" onClick={sort('thoigianCapnhat')}>
                  {translate('csytApp.csytOneBss.thoigianCapnhat')} <FontAwesomeIcon icon={getSortIconByFieldName('thoigianCapnhat')} />
                </th>
                <th className="hand" onClick={sort('maTinh')}>
                  {translate('csytApp.csytOneBss.maTinh')} <FontAwesomeIcon icon={getSortIconByFieldName('maTinh')} />
                </th>
                <th className="hand" onClick={sort('maSpdv')}>
                  {translate('csytApp.csytOneBss.maSpdv')} <FontAwesomeIcon icon={getSortIconByFieldName('maSpdv')} />
                </th>
                <th className="hand" onClick={sort('maHopdong')}>
                  {translate('csytApp.csytOneBss.maHopdong')} <FontAwesomeIcon icon={getSortIconByFieldName('maHopdong')} />
                </th>
                <th className="hand" onClick={sort('ngaykyHopdong')}>
                  {translate('csytApp.csytOneBss.ngaykyHopdong')} <FontAwesomeIcon icon={getSortIconByFieldName('ngaykyHopdong')} />
                </th>
                <th>{translate('csytApp.csytOneBss.action')}</th>
              </tr>
            </thead>
            <tbody style={{ fontSize: '15px' }}>
              {list.map((csytOneBss, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/csyt-one-bss/${csytOneBss.id}`} color="link" size="sm">
                      {csytOneBss.id}
                    </Button>
                  </td>
                  <td>{csytOneBss.maCsyt}</td>
                  <td>{csytOneBss.maThuebao}</td>
                  <td>{csytOneBss.maDuan}</td>
                  <td>{trangthai[csytOneBss.trangthai]}</td>
                  <td>
                    {csytOneBss.thoigianCapnhat ? (
                      <TextFormat type="date" value={csytOneBss.thoigianCapnhat} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{csytOneBss.maTinh}</td>
                  <td>{csytOneBss.maSpdv}</td>
                  <td>{csytOneBss.maHopdong}</td>
                  <td>
                    {csytOneBss.ngaykyHopdong ? (
                      <TextFormat type="date" value={csytOneBss.ngaykyHopdong} format={APP_LOCAL_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      {/* <Button tag={Link} to={`/csyt-one-bss/${csytOneBss.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button> */}
                      <Button tag={Link} to={`/csyt-one-bss/${csytOneBss.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">{translate('csytApp.csytOneBss.editLabel')}</span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/csyt-one-bss/${csytOneBss.id}/delete`)}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">{translate('csytApp.csytOneBss.deleteLabel')}</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">{translate('csytApp.csytOneBss.home.notFound')}</div>
        )}
      </div>
    </div>
  );
};

export default CsytOneBss;
